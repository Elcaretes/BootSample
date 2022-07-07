package com.elcaretes.bootSample.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration // 하나 이상의 @Bean메소드를 제공, @Bean 함수가 리턴하는 객체를 Singleton으로 관리
@MapperScan(basePackages = {"com.elcaretes.bootSample.mapper"}) // 해당 경로의 mapper 인터페이스를 Bean에 등록, Spring3.1이상
public class DBConfig {
	
	protected final Logger log = LoggerFactory.getLogger( this.getClass() );
	
	@Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
	
	@Value("${spring.datasource.url}")
    private String url;
	
	@Value("${spring.datasource.username}")
    private String username;
	
	@Value("${spring.datasource.password}")
    private String password;
	
	@Autowired
	ApplicationContext applicationContext;
	
	// DB source
	@Bean
    public DataSource dataSource() {
		
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName( driverClassName );
        dataSource.setUrl( url );
        dataSource.setUsername( username );
        dataSource.setPassword( password );

        return dataSource;
    }
	
	// DataSource를 이용하여 mysql서버와 mybatis를 연결
	// SqlSessionFactory는 데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 가장 중요한 객체
	// mybatis설정 파일 등록
	@Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        
        // mapper.xml 의 resultType 패키지 주소 생략
        factoryBean.setTypeAliasesPackage("com.elcaretes.bootSample.model");
		
		// 매퍼 xml의 경로 설정
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:com/elcaretes/bootSample/sql/*.xml"));
        
        // 카멜케이스 적용
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        
        return factoryBean;
    }
	
	// mybatis의 쿼리문을 수행하는 역할
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    // 트랜잭션 매니저
 	@Bean
 	public DataSourceTransactionManager transactionManager() {
 		return new DataSourceTransactionManager(dataSource());
 	}
 	
}