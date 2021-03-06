package com.mermer.app.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.api.dto.OrderFlatDto;
import com.mermer.app.api.dto.OrderQueryDto;
import com.mermer.app.domain.Address;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderItem;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.OrderStatus;
import com.mermer.app.repository.OrderQueryRepository;
import com.mermer.app.repository.OrderRepository;
import com.mermer.app.service.api.OrderApiService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	private final OrderQueryRepository orderQueryRepository;
	
	private final OrderApiService orderApiService; 
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1(){
		List<Order> orders = orderApiService.getV1selectOrderList();
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
	
	@GetMapping("/api/v3/orders")
	public List<OrderDto> ordersV3(){
		List<Order> orders = orderRepository.findAllWithItems();
		//초기화
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	} 
	
	@GetMapping("/api/v3.1/orders")
	public List<OrderDto> ordersV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit
			){
		List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
		//초기화
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	} 
	
	@GetMapping("/api/v4/orders")
	public List<OrderQueryDto> ordersV4(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit
			){
		return orderQueryRepository.findOrderQueryDtos();
	} 
	
	@GetMapping("/api/v5/orders")
	public List<OrderQueryDto> ordersV5(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit
			){
		return orderQueryRepository.findAllByDto_optimization();
	} 
	
	@GetMapping("/api/v6/orders")
	public List<OrderFlatDto> ordersV6(@RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "100") int limit
			){
		return orderQueryRepository.findAllByDto_flat();
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
