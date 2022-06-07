package com.mermer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.MemberDto;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public List<Member> findByName(String name);

	public List<Member> findByNameAndAgeGreaterThan(String name, int age);
	
	@Query(name = "Member.findByName")
	List<Member> findByUserName2(@Param("name") String name);
	
	@Query("select m from Member m where m.name = :name and m.age >= :age")
	List<Member> findByUserName3(@Param("name") String name, @Param("age") int age);
	
	@Query("select m.name from Member m")
	List<String> findNameList();
	
	@Query("select new com.mermer.app.domain.MemberDto(m.id, m.name, t.name) from Member m join m.team t")
	List<MemberDto> findMemberDto();
	
}
