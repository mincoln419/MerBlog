package com.mermer.app.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.Team;

@Repository
public class TeamRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Team save(Team team) {
		em.persist(team);
		return team;
	}
	
	public void delete(Team team) {
			em.remove(team);
	}
	
	public List<Team> findAll(){
		return em.createQuery("select t from Team t", Team.class).getResultList();
	}
	
	public Optional<Team> findById(Long id){
		Team Team = em.find(Team.class, id);
		return Optional.of(Team);
	}
	
	public long count(){
		return em.createQuery("select count(t) from Team t", Long.class).getSingleResult();
	}
}
