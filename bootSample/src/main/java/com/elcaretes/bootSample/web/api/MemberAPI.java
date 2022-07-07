package com.elcaretes.bootSample.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elcaretes.bootSample.model.Member;
import com.elcaretes.bootSample.service.MemberService;

@Controller
@RequestMapping("/api")
public class MemberAPI {

	@Autowired
	MemberService memberService;
	
	/**
	 * Description : 회원 등록
	 *
	 * @author Elcaretes
	 * Date : 2022. 7. 8. 오전 2:55:50
	 */
	@RequestMapping(value="/member", method = RequestMethod.POST )
	public ResponseEntity<Member> registMember( Member member ) {
		memberService.regist( member );
		return ResponseEntity.ok().body( member );
	}
	
	/**
	 * Description : 모든 회원 조회
	 *
	 * @author Elcaretes
	 * Date : 2022. 7. 8. 오전 2:56:03
	 */
	@RequestMapping(value="/members", method = RequestMethod.GET )
	public ResponseEntity<List<Member>> getMembers() {
		return ResponseEntity.ok().body( memberService.findAll() );
	}
	
}
