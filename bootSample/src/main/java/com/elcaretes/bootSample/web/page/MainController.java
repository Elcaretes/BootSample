package com.elcaretes.bootSample.web.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	protected final Logger log = LoggerFactory.getLogger( this.getClass() );
	
	/**
	 * Description : 메인화면
	 *
	 * @author Elcaretes
	 * Date : 2022. 7. 8. 오전 2:57:23
	 */
	@RequestMapping(value="/", method=RequestMethod.GET )
	public String index() {
		return "index";
	}
	
}
