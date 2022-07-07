package com.elcaretes.bootSample.mapper;

import java.util.List;

import com.elcaretes.bootSample.model.Member;

public interface MemberMapper {	
	
	void registMember( Member meber );
	List<Member> findAllMember();
	
}
