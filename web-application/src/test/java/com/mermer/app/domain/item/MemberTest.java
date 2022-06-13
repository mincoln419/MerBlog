package com.mermer.app.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.MemberDto;
import com.mermer.app.domain.Team;
import com.mermer.app.repository.MemberJpaRepository;
import com.mermer.app.repository.MemberRepository;
import com.mermer.app.repository.NestClosedProjections;
import com.mermer.app.repository.TeamJpaRepository;
import com.mermer.app.repository.UsernameOnly;

@SpringBootTest
@Transactional
@Rollback(true)
class MemberTest {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MemberJpaRepository memberJpaRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	TeamJpaRepository teamJpaRepository;
	
	@Test
	void testEntity() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 12, teamB);
		Member member3 = new Member("member3", 15, teamA);
		Member member4 = new Member("member4", 20, teamB);
		
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
		
		//초기화
		em.flush();
		em.clear();
		
		//확인
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		
		members.forEach(m -> {
			System.out.println("member = " + m);
			System.out.println("-> member.team = " + m.getTeam());
			
		});
		
	}
	
	@Test
	public void basicCRUD() {
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 12, teamB);
		
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);
		
		Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
		Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
		
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);
		
		assertThat(memberJpaRepository.count()).isEqualTo(memberJpaRepository.findAll().size());
		
		//삭제검증
		memberJpaRepository.delete(member1.getId());
		memberJpaRepository.delete(member2.getId());

	}

	
	@Test
	public void findNameAndGraterThen_Test() {
		
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("AAA", 20, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<Member> members = memberRepository.findByNameAndAgeGreaterThan("AAA", 9);
		assertThat(members.size()).isEqualTo(2);
	}
	
	@Test
	public void findMembers_test_namedQuery() {
		Member member1 = new Member("BBB", 10, null);
		Member member2 = new Member("BBB", 20, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<Member> members = memberRepository.findByUserName2("BBB");
		assertThat(members.size()).isEqualTo(2);
	}
	
	
	@Test
	public void findMembers_test_repositoryMethodQuery() {
		Member member1 = new Member("CCC", 10, null);
		Member member2 = new Member("CCC", 20, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<Member> members = memberRepository.findByUserName3("CCC", 9);
		assertThat(members.size()).isEqualTo(2);
	}

	@Test
	public void findNameList_test_repositoryMethodQuery() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 20, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<String> memberNames = memberRepository.findNameList();
		assertThat(memberNames.size()).isEqualTo(2);
	}
	
	@Test
	public void findMemberDto() {
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		teamJpaRepository.save(teamA);
		teamJpaRepository.save(teamB);
		Member member1 = new Member("AAA", 10, teamA);
		Member member2 = new Member("BBB", 20, teamB);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<MemberDto> memberNames = memberRepository.findMemberDto();
		assertThat(memberNames.size()).isEqualTo(2);
	}
	

	@Test
	public void findNameList_test_repositoryMethodQuery_withCollection() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 20, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		List<String> names = Arrays.asList("AAA", "BBB");
		List<Member> memberNames = memberRepository.findByNames(names);
		assertThat(memberNames.size()).isEqualTo(2);
	}
	
	
	@Test
	public void test_returnType() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 20, null);
		Member member3 = new Member("AAA", 10, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		List<Member> memberNames = memberRepository.findUserListByName("AAA");
		assertThat(memberNames.size()).isEqualTo(2);
		
		Member findMember = memberRepository.findMemberByName("BBB");
		assertThat(findMember).isEqualTo(member2);
		
		Optional<Member> findMemberOptional = memberRepository.findOptionalMemberByName("BBB");
		if(findMemberOptional.isPresent())
			assertThat(findMemberOptional.get()).isEqualTo(member2);
	}
	
	@Test
	public void test_page_jpa() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 20, null);
		Member member3 = new Member("CCC", 10, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		
		int age = 10;
		int offset = 0;
		int limit = 1;
		
		List<Member> memberNames = memberJpaRepository.findByPage(age, offset, limit);
		assertThat(memberNames.size()).isEqualTo(1);
		assertThat(memberNames.get(0).getName()).isEqualTo(member3.getName());
		
		long count = memberJpaRepository.totalCount(age);
		assertThat(count).isEqualTo(2);
		
	}
	
	@Test
	public void bulkAgePlus_test(){
		memberJpaRepository.save(new Member("member1", 10, null));
		memberJpaRepository.save(new Member("member1", 20, null));
		memberJpaRepository.save(new Member("member1", 30, null));
		memberJpaRepository.save(new Member("member1", 40, null));
		memberJpaRepository.save(new Member("member1", 50, null));
		
		int resultCount = memberJpaRepository.bulkAgePlus(30);
	
		
		//then
		assertThat(resultCount).isEqualTo(3);
	}
	
	@Test
	public void queryByExample() {
		//Given
		Team teamA = new Team("teamA");
		em.persist(teamA);
		
		Member m1 = new Member("m1",0, teamA);
		Member m2 = new Member("m2",0, teamA);
		em.persist(m1);
		em.persist(m2);
		
		em.flush();
		em.clear();
		
		//when
		//Probe
		Member member = new Member("m1");
		member.setTeam(teamA);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("age");
		
		Example<Member> example = Example.of(member, matcher);
		
		List<Member> result = memberRepository.findAll(example);
		
		assertThat(result.size()).isEqualTo(1);
	}
	
	@Test
	public void test_projections() {
		//Given
		Team teamA = new Team("teamA");
		em.persist(teamA);
		
		Member m1 = new Member("m1",0, teamA);
		Member m2 = new Member("m2",0, teamA);
		em.persist(m1);
		em.persist(m2);
		
		em.flush();
		em.clear();
		memberRepository.findProjectionsByName("m1", NestClosedProjections.class).forEach(m -> {
			System.out.println(m);
			System.out.println(m.getName());
			System.out.println(m.getTeam());
		});
	}
	
	
	
}
