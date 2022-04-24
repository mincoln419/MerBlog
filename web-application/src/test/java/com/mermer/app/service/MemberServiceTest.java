/**
 * @packageName : com.mermer.app.service
 * @fileName : MemberServiceTest.java 
 * @author : Mermer 
 * @date : 2022.04.17 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.17 Mermer 최초 생성
 */
package com.mermer.app.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Member;
import com.mermer.app.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EntityManager em;
	/**
	 * Test method for {@link com.mermer.app.service.MemberService#join(com.mermer.app.domain.Member)}.
	 */
	@Test
	@DisplayName("회원가입 테스트")
	@Rollback(true)
	public void testJoin_success() {
		//given
		Member member = new Member();
		member.setName("alice");
		member.setAddress(Address.builder()
						.city("Seoul")
						.street("SeoDeaMoon")
						.zipcode("112345")
						.build()
						);
		//when
		Long savedId = memberService.join(member);
		em.flush();
		//then
		assertEquals(member, memberRepository.findOne(savedId));
		
	}
	
	
	@Test
	@DisplayName("회원가입 예외- 회원중복 테스트")
	public void testJoin_faliure_dup_name() {
		//given
		Member member1 = new Member();
		member1.setName("alice");
		member1.setAddress(Address.builder()
						.city("Seoul")
						.street("SeoDeaMoon")
						.zipcode("112345")
						.build()
						);
		
		Member member2 = new Member();
		member2.setName("alice");
		member2.setAddress(Address.builder()
						.city("Seoul")
						.street("SeoDeaMoon")
						.zipcode("112345")
						.build()
						);
		
		//when
		memberService.join(member1);
		
		//then
		assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(IllegalStateException.class);
		
	}

	

	/**
	 * Test method for {@link com.mermer.app.service.MemberService#findMembers()}.
	 */
	@Test
	public void testFindMembers() {
	}

	/**
	 * Test method for {@link com.mermer.app.service.MemberService#findOne(java.lang.Long)}.
	 */
	@Test
	public void testFindOne() {
	}

	/**
	 * Test method for {@link com.mermer.app.service.MemberService#MemberService(MemberRepository)}.
	 */
	@Test
	public void testMemberService() {
	}

}
