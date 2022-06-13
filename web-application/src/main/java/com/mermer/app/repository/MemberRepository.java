package com.mermer.app.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.MemberDto;

public interface MemberRepository<MemberProjection> extends JpaRepository<Member, Long>, MemberRepositoryCustom {

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
	
	@Modifying(clearAutomatically = true)
	@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
	int bulkAgePlus(@Param("age") int age);

	//fetch조인을 사용하려 query 를 사용하는 방법으로 가능 -> spring-data-jpa의 entity Graph 로 처리가능
	@Query("select m from Member m left join fetch m.team")
	public List<Member> findMemberFetchJoin();
	
	@Override
	@EntityGraph(attributePaths = {"team"})
	List<Member> findAll();
	
	@QueryHints(value = @QueryHint(name ="org.hibernate.readOnly", value = "true"))
	Member findReadOnlyByName(String name);
	

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	List<Member> findLockByName(String name);
	
	
	<T> List<T> findProjectionsByName(@Param("name") String name, Class<T> type);
	
	@Query(value = "select m.member_id as id, m.name, t.name as teamName"
			+ "from member m left join team t", 
			countQuery = "select count(*) from member",
			nativeQuery = true)
	Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
