/**
 * @packageName : com.mermer.app.repository
 * @fileName : ItemRepository.java 
 * @author : Mermer 
 * @date : 2022.04.23 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.23 Mermer 최초 생성
 */
package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.item.Item;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		}else {
			em.merge(item);
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}
