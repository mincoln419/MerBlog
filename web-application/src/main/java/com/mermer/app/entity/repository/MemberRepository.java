/**
 * @packageName : com.mermer.app.entity.repository
 * @fileName : MemberRepository.java 
 * @author : Mermer 
 * @date : 2022.04.10 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.10 Mermer 최초 생성
 */
package com.mermer.app.entity.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mermer.app.entity.Member;

/* 
 * @description: 
 */
@Repository
public class MemberRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Long save(Member member){
		em.persist(member);
		return member.getId();
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
