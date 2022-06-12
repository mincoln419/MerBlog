package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.Member;

@Repository
public class MemberQueryRepository {

	@PersistenceContext
	EntityManager em;
	
	List<Member> findAllMembers(){
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
}
