package com.mermer.app.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.MemberDto;
import com.mermer.app.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberJpaController {

	private final MemberRepository memberRepository;
	
	@GetMapping("/members/{id}")
	public String findMember(@PathVariable("id") Long id) {
		return memberRepository.findById(id).get().getName();
		
	}
	
	
	//도메인 class converting 기능
	@GetMapping("/members2/{id}")
	public String findMember2(@PathVariable("id") Member member) {
		return member.getName();
		
	}
	//@Qualifier 쓰면 페이징 여러개로 사용할 수 있음
	@GetMapping("/membersAll")
	public Page<MemberDto> list(@Qualifier("mermer") @PageableDefault(page = 0, size = 1000) Pageable pageable){
		return memberRepository.findAll(pageable).map(member -> new MemberDto(member));
	}
}
