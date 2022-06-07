package com.mermer.app.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.Member;

@Repository
public class MemberJpaRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Member save(Member member) {
		em.persist(member);
		return member;
	}
	
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
	
	public Optional<Member> findById(Long id){
		Member member = em.find(Member.class, id);
		return Optional.of(member);
	}
	
	public long count(){
		return em.createQuery("select count(m) from Member m", Long.class).getSingleResult();
	}
	
	public List<Member> findByNameAndAgeGreaterThan(String name, int age){
		return em.createQuery("select m from Member m where m.name = :name and m.age > :age", Member.class)
				.setParameter("name", name)
				.setParameter("age", age)
				.getResultList();
	}

	public void delete(Long id) {
		em.remove(id);
	}
	
	public List<Member> findMembers(String name){
		return em.createNamedQuery("Member.findByName", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}


