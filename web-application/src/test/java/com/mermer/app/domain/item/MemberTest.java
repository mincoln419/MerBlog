package com.mermer.app.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.Team;
import com.mermer.app.repository.MemberJpaRepository;
import com.mermer.app.repository.MemberRepository;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MemberJpaRepository memberJpaRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
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
	
}
