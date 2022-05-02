/**
 * @packageName : com.mermer.app.service
 * @fileName : OrderServiceTest.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Member;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderStatus;
import com.mermer.app.domain.item.Book;
import com.mermer.app.exception.NotEnoughStockException;
import com.mermer.app.repository.OrderRepository;

@SpringBootTest
@Transactional
class OrderServiceTest {

	@Autowired
	EntityManager em;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	/**
	 * Test method for
	 * {@link com.mermer.app.service.OrderService#order(java.lang.Long, java.lang.Long, int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("주문 생성 정상테스트")
	void testOrder_success() throws Exception {
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
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		Order getOrder = orderRepository.findOne(orderId);

		em.flush();
		em.close();

		// "상품 주문시 상태는 order"
		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
		// 주문한 상품 종류 수가 정확해야 한다.
		assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
		// 주문 가격은 가격 * 수량
		assertThat(getOrder.getTotalPrice()).isEqualTo(orderCount * 1000);
		// 주문 수량만큼 재고가 줄어야 한다
		assertThat(book.getStockQuantity()).isEqualTo(10 - 2);
	}

	@Test
	@DisplayName("재고 초과 주문 오류 테스트")
	void testOrder_overStock_error() {
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
		int orderCount = 12;
	
		// then
		assertThatThrownBy(() -> orderService.order(member.getId(), book.getId(), orderCount))
			.isInstanceOf(NotEnoughStockException.class);
	}

	/**
	 * Test method for
	 * {@link com.mermer.app.service.OrderService#cancelOrder(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	@DisplayName("주문 취소 정상테스트")
	void testCancelOrder_success() throws Exception {
		// given
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

		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// when
		orderService.cancelOrder(orderId);
		
		// then
		Order getOrder = orderRepository.findOne(orderId);
		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
		assertThat(book.getStockQuantity()).isEqualTo(10);	
	}

}
