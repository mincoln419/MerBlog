package com.mermer.app.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.MemberDto;
import com.mermer.app.domain.Team;
import com.mermer.app.repository.MemberRepository;
import com.mermer.app.repository.TeamJpaRepository;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberJpaRepositoryTest {
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TeamJpaRepository teamJpaRepository;
	
	@Test
	public void test_page_jpa() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 10, null);
		Member member3 = new Member("CCC", 10, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		
		int age = 10;
		int offset = 0;
		int limit = 1;
		
		PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "name"));
		
		Page<Member> memberNames = memberRepository.findByAge(age, pageRequest);
		assertThat(memberNames.getTotalPages()).isEqualTo(3);
		memberNames.get().anyMatch(m -> m.getName().equals(member3.getName()));
		
		
	}
	
	
	@Test
	public void test_slice_jpa() {
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 10, null);
		Member member3 = new Member("CCC", 10, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		
		int age = 10;
		int offset = 0;
		int limit = 1;
		
		PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "name"));
		
		//Slice<Member> memberNames = memberRepository.findByAge(age, pageRequest);
		//assertThat(memberNames.getSize()).isEqualTo(3);//slice는 total count 쿼리를 날리지 않는다.
		//memberNames.get().anyMatch(m -> m.getName().equals(member3.getName()));
		
		
	}

	
	@Test
	public void test_page_jpa_newrntr() {
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);
		Member member1 = new Member("AAA", 10, null);
		Member member2 = new Member("BBB", 10, null);
		Member member3 = new Member("CCC", 10, null);
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		
		int age = 10;
		int offset = 0;
		int limit = 10;
		
		PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "name"));
		
		Page<Member> memberNames = memberRepository.findByAge_new(age, pageRequest);
		
		memberNames.map(member -> new MemberDto(member.getId() , member.getName(), member.getTeam().getName()));
		assertThat(memberNames.getTotalElements()).isEqualTo(3);
		memberNames.get().anyMatch(m -> m.getName().equals(member3.getName()));
		
		
	}
	
	@Test
	public void bulkAgePlus_test(){
		memberRepository.save(new Member("member1", 10, null));
		memberRepository.save(new Member("member2", 20, null));
		memberRepository.save(new Member("member3", 30, null));
		memberRepository.save(new Member("member4", 40, null));
		memberRepository.save(new Member("member5", 50, null));
		
		int resultCount = memberRepository.bulkAgePlus(30);
		//em.flush();
		//em.clear();//벌크연산한 후 영속성 컨텍스트 clear해야함 -> clearautomatically = true 로 바꾸면 안해도 됨
		
		List<Member> result = memberRepository.findByName("member5");
		Member member5 = result.get(0);;
		System.out.println("member5 = "+ member5);
		//then
		assertThat(resultCount).isEqualTo(3);
	}
	
	@Test
	public void findMemberLazy(){
		//given
		//member1 -> teamA
		//member2 -> teamB
		
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		
		teamJpaRepository.save(teamA);
		teamJpaRepository.save(teamB);
		
		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamB);
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		em.flush();
		em.clear();
		
		//when
		List<Member> members =  memberRepository.findAll();
		members.forEach(member -> {
			System.out.println(member.getName());
			System.out.println(member.getTeam().getName());
		} );
		
		em.clear();
		
		List<Member> members2=  memberRepository.findMemberFetchJoin();
		members2.forEach(member -> {
			System.out.println(member.getName());
			System.out.println(member.getTeam().getName());
		} );
	}
	
	@Test
	public void queryHint() {
		//given
		Member member1 = memberRepository.save(new Member("member1", 10, null));
		em.flush();
		em.clear();
		
		//when
		Member findMember = memberRepository.findReadOnlyByName("member1");
		findMember.setName("member2");
		
		em.flush();
	}
	
	@Test
	public void lock() {
		//given
		Member member1 = memberRepository.save(new Member("member1", 10, null));
		em.flush();
		em.clear();
		
		//when
		Member findMember = memberRepository.findLockByName("member1").get(0);
		
		em.flush();
	}
}
