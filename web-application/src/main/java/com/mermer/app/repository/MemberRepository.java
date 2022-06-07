package com.mermer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mermer.app.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public List<Member> findByName(String name);

	public List<Member> findByNameAndAgeGreaterThan(String name, int age);
	
	@Query(name = "Member.findByName")
	List<Member> findByUserName2(@Param("name") String name);
	
}
