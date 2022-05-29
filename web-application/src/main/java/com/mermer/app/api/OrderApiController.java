package com.mermer.app.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderItem;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1(){
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
