/**
 * @packageName : com.mermer.app.service
 * @fileName : MemberService.java 
 * @author : Mermer 
 * @date : 2022.04.17 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.17 Mermer 최초 생성
 */
package com.mermer.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Member;
import com.mermer.app.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	/* 회원가입 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); //중복회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	/**
	 * @method validateDuplicateMember
	 * @param member
	 * void
	 * @description 
	 */
	private void validateDuplicateMember(Member member) {
		//Exception
		List<Member> findMembers = memberRepository.findByName( member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		}
	}
	
	/* 전체회원 조회 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	/* 전체회원 단건조회 */
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}

	/**
	 * @method update
	 * @param id
	 * @param name
	 * @return
	 * Long
	 * @description 
	 */
	@Transactional
	public void update(Long id, String name) {
		
		Member member = new Member();
		member.setName(name);
		validateDuplicateMember(member); //중복회원 검증				
		member = memberRepository.findOne(id);
		member.setName(name);
		
	}
	
}
