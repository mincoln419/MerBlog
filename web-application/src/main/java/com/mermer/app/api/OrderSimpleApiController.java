/**
 * @packageName : com.mermer.app.api
 * @fileName : OrderApiController.java 
 * @author : Mermer 
 * @date : 2022.05.15 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.15 Mermer 최초 생성
 */
package com.mermer.app.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.api.dto.OrderSimpleQueryDto;
import com.mermer.app.domain.Address;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.OrderStatus;
import com.mermer.app.repository.OrderRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/* xToOne(ManyToOne, OneToOne 에서 성능최적화
 * @description: order를 조회하고, order -> member ,order -> delivery 
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

	private final OrderRepository orderRepository;
	
	//1. 무한루프 안걸리려면 양방향 연관관계 있을 경우에는 json ignore를 한쪽에 걸어줘야 함
	//2. lazy의 경우 ByteBuddyIntercept -> proxy 기술을 써서 빈객체를 넣어두는데..
	//   jackson이 member가 아니라 ByteBuddyIntercept여셔 json으로 만들 수 없다는 오류 발생..
	//3. 2번의 경우 Hibernate5Module 로 해결 가능하긴 하지만,, 성능이 최악이 된다. 불필요한 Query, Entity 정보 직접 노출이슈
	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersV1(){
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		
		for(Order order : all) {
			order.getMember().getName();//Hibernate5Module 안쓰고 lazy를 강제초기화
			order.getDelivery().getAddress();
		}
		
		return all;
	}
	
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2(){
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		
		return orders.stream()
				.map(SimpleOrderDto::new)
				.collect(Collectors.toList());
	}
	
	//여러 기능에서 재활용하여 사용하기 수월
	@GetMapping("/api/v3/simple-orders")
	public List<SimpleOrderDto> ordersV3(){
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		return orders.stream()
				.map(SimpleOrderDto::new)
				.collect(Collectors.toList());
	}
	//v3와 v4 는 트레이드 오프 관계 - 우열을 가리기 어렵다
	//v4는 재사용성이 적음
	//v4가 좀더 성능 최적화가 좋음
	//v4는 entity가 아니어서 영속성 컨텍스트를 사용할 수 없음
	//v4 api 스펙이 바뀌면 로직을 뜯어고쳐야하는 문제 발생
	//근데 정작 성능 문제 개선은 fetch 조인으로 해결할 수 있는 것이라서.. 그닥..
	//v3면 충분하다
	@GetMapping("/api/v4/simple-orders")
	public List<OrderSimpleQueryDto> ordersV4(){
		List<OrderSimpleQueryDto> orderDtos = orderRepository.findOrderDtos();
		return orderDtos.stream()
				.collect(Collectors.toList());
	}
	
	@Data
	static class SimpleOrderDto{
		public SimpleOrderDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName();//LAZY 초기화 
			orderDate = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress();//LAZY 초기화
		}
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
	}
}
