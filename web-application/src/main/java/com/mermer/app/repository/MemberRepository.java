package com.mermer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mermer.app.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public List<Member> findByName(String name);
	
}
