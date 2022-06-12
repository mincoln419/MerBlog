package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.mermer.app.domain.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

	private final EntityManager em;
	
	@Override
	public List<Member> findMemberCustom() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

}
