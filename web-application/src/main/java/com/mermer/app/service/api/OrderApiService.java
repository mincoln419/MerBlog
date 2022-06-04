package com.mermer.app.service.api;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderItem;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.repository.OrderQueryRepository;
import com.mermer.app.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderApiService {


	private final OrderRepository orderRepository;
	private final OrderQueryRepository orderQueryRepository;
	
	public List<Order> getV1selectOrderList() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		//초기화
		orders.stream().forEach(o -> {
			o.getMember().getName();
			o.getDelivery().getAddress();
			List<OrderItem> orderItems = o.getOrderItems();
			orderItems.stream().forEach(oi -> {
				oi.getItem().getName();
			});
		});
		return orders;
	} 
}
