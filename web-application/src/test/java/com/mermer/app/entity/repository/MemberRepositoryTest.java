/**
 * @packageName : com.mermer.app.entity.repository
 * @fileName : MemberRepositoryTest.java 
 * @author : Mermer 
 * @date : 2022.04.10 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.10 Mermer 최초 생성
 */
package com.mermer.app.entity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.entity.Member;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@Transactional
	@DisplayName("Meber jpa test")
	public void tetsMember() throws  Exception{

		//given
		Member member = Member.builder()
				.name("mermer")
				.build();
		
		//when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);
		
		//then
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getName()).isEqualTo(member.getName());
		
	}

}
