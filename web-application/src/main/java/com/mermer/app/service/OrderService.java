/**
 * @packageName : com.mermer.app.service
 * @fileName : OrderService.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Delivery;
import com.mermer.app.domain.Member;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderItem;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.item.Item;
import com.mermer.app.repository.ItemRepository;
import com.mermer.app.repository.MemberRepositoryOld;
import com.mermer.app.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	private final MemberRepositoryOld memberRepository;
	
	private final ItemRepository itemRepository;
	
	/* 
	 * 주문접수
	 * */
	@Transactional
	public Long order(Long memberId, Long itemId, int count ) throws Exception {
		
		//엔티티조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		//주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문저장
		orderRepository.save(order);
		
		return order.getId();		
	}
	
	/* 
	 * 주문취소
	 * */
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancel();
	}
	
	//검색
	public List<Order> findOrders(OrderSearch orderSearch){
		return orderRepository.findAllByString(orderSearch);
	}
}

