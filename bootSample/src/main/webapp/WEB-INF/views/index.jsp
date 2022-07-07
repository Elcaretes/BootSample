<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>Insert title here</title>
</head>
<body>

	<script>
        // 요청결과
        var result;

        // API요청하기
        function loadAPI() {

            // XMLHttpRequest 객체 생성
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {

                // readyState : 4, 요청 완료 및 응답 준비
                // status : 200, 요청 완료
                if( this.readyState == 4 && this.status == 200 ) {

                    // 응답데이터를 JSON객체로 변환
                    console.log( this );
                    result = JSON.parse(this.response);
                    console.log( result );
                    console.log( result[0].memberPk );
                    console.log( result[0].id );
                    console.log( result[0].name );

                    // JSON객체의 크기
                    var resultLength = document.getElementById('resultLength');
                    console.log( 'JSON객체의 크기 : ' + Object.keys(result).length );
                    resultLength.innerHTML = '응답데이터 크기 : ' + Object.keys(result).length;
                    
                }
            };

            // 요청정보작성
            xhttp.open('GET', 'http://localhost:8080/api/members', true);
            // 서버에 요청
            xhttp.send();
        }
    </script>
    
	<p>Index Page</p>
	<button type='button' onclick='loadAPI()'>회원목록</button>
	<div id="resultLength"></div>
	
</body>
</html>