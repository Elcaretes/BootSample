package com.elcaretes.bootSample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elcaretes.bootSample.mapper.MemberMapper;
import com.elcaretes.bootSample.model.Member;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public void regist( Member member ) {
		memberMapper.registMember( member );
	}
	
	public List<Member> findAll() {
		return memberMapper.findAllMember();
	}
	
}
