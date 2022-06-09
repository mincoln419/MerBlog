package com.mermer.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
	
	@Query("select m from Member m where m.name in :names")
	List<Member> findByNames(@Param("names") List<String> names);
	
	List<Member> findUserListByName(String name);
	Member findMemberByName(String name);
	Optional<Member> findOptionalMemberByName(String name);

	public Page<Member> findByAge(int age, Pageable pageable);
	
	@Query(value = "select m from Member m left join m.team", countQuery="select count(m) from Member m" )
	public Page<Member> findByAge_new(int age, Pageable pageable);
	
}
