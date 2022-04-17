/**
 * @packageName : com.mermer.app.repository
 * @fileName : MemberRepository.java 
 * @author : Mermer 
 * @date : 2022.04.17 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.17 Mermer 최초 생성
 */
package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.Member;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){
		List<Member> result = em.createQuery("select m from Member m", Member.class)
				.getResultList();
		return result;
	}
	
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	} 
	
}
