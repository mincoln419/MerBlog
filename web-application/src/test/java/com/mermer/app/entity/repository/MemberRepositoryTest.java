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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Member;
import com.mermer.app.jpa.MemberRepository1;

@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

	@Autowired
	MemberRepository1 memberRepository;
	
	@Test
	@Transactional
	@DisplayName("Meber jpa test")
	public void tetsMember() throws  Exception{

		//given
		Member member = Member.builder()
				.name("mermer")
				.build();
		
		//when
		Member result = memberRepository.save(member);
		Member findMember = memberRepository.findById(result.getId()).get();
		
		//then
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getName()).isEqualTo(member.getName());
		assertThat(findMember).isEqualTo(member);
		
	}

}
