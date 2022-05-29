package com.mermer.app.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderItem;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.OrderStatus;
import com.mermer.app.repository.OrderRepository;

import lombok.Data;
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
	
	//DTO 안에도 entity도 들어가서는 안된다.
	//entity에 대한 의존도를 완전히 끊어야 한다
	//orderItem도 dto를 만들어야 한다
	//그러나 여전히 n+1 문제는 발생한다
	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2(){
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		//초기화
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	} 
	
	@Data
	static class OrderDto{
		
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;
		
		public OrderDto(Order o) {
			this.orderId = o.getId();
			this.name = o.getMember().getName();
			this.orderDate = o.getOrderDate();
			this.orderStatus = o.getStatus();
			this.address = o.getDelivery().getAddress();
			o.getOrderItems().stream().forEach(oi -> oi.getItem().getName());
			this.orderItems = o.getOrderItems().stream()
					.map(OrderItemDto::new)
					.collect(Collectors.toList());
		}
	}
	
	
	@Data
	static class OrderItemDto{
		private String itemName;
		private int orderPrice;
		private int count;
		
		public OrderItemDto(OrderItem oi) {
			this.itemName = oi.getItem().getName();
			this.orderPrice = oi.getItem().getPrice();
			this.count = oi.getCount();
		}
	}
}
