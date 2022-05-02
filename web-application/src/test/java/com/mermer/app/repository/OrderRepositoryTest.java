/**
 * @packageName : com.mermer.app.repository
 * @fileName : OrderRepositoryTest.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Member;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.OrderStatus;
import com.mermer.app.domain.item.Book;
import com.mermer.app.service.OrderService;

/* 
 * @description: 
 */
@SpringBootTest
@Transactional
class OrderRepositoryTest {

	@Autowired OrderRepository orderRepository;
	

	@Autowired
	EntityManager em;
	
	@Autowired
	OrderService orderService;
	
	/**
	 * Test method for {@link com.mermer.app.repository.OrderRepository#save(com.mermer.app.domain.Order)}.
	 */
	@Test
	void testSave() {
	}

	/**
	 * Test method for {@link com.mermer.app.repository.OrderRepository#findOne(java.lang.Long)}.
	 */
	@Test
	void testFindOne() {
	}

	/**
	 * Test method for {@link com.mermer.app.repository.OrderRepository#findAll(com.mermer.app.domain.OrderSearch)}.
	 * @throws Exception 
	 */
	@Test
	@DisplayName("주문 전체조회")
	void testOrder_FindAll_orderStatus_success() throws Exception {
		// given
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);

		Book book = new Book();
		book.setName("시골 JPA");
		book.setPrice(1000);
		book.setStockQuantity(10);
		em.persist(book);

		// when
		int orderCount = 2;
		orderService.order(member.getId(), book.getId(), orderCount);
		
		Member member2 = new Member();
		member2.setName("회원2");
		member2.setAddress(new Address("부산", "바닷가", "333-212"));
		em.persist(member2);
		
		orderService.order(member2.getId(), book.getId(), orderCount);
		
		
		
		//then
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setOrderStatus(OrderStatus.ORDER);
		List<Order> resultList = orderRepository.findAllByString(orderSearch);
		assertThat(resultList.size()).isEqualTo(2);
		
		
	}

	
	/**
	 * Test method for {@link com.mermer.app.repository.OrderRepository#findAll(com.mermer.app.domain.OrderSearch)}.
	 * @throws Exception 
	 */
	@Test
	@DisplayName("주문 조회 - 회원이름 + 주문상태")
	void testOrder_FindAll_memberName_OrderStatus_success() throws Exception {
		// given
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);

		Book book = new Book();
		book.setName("시골 JPA");
		book.setPrice(1000);
		book.setStockQuantity(10);
		em.persist(book);

		// when
		int orderCount = 2;
		orderService.order(member.getId(), book.getId(), orderCount);
		
		Member member2 = new Member();
		member2.setName("회원2");
		member2.setAddress(new Address("부산", "바닷가", "333-212"));
		em.persist(member2);
		
		orderService.order(member2.getId(), book.getId(), orderCount);
		
		
		
		//then
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setOrderStatus(OrderStatus.ORDER);
		orderSearch.setMemberName("회원1");
		List<Order> resultList = orderRepository.findAllByString(orderSearch);
		assertThat(resultList.size()).isEqualTo(1);
		
		
	}
	
}
