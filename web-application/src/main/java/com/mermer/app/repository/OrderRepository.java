/**
 * @packageName : com.mermer.app.repository
 * @fileName : OrderRepository.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.mermer.app.domain.Order;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	
	//public List<Order> findAll(OrderSearch orderSearch){}
	
}
