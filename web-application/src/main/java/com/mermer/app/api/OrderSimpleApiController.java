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

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.repository.OrderRepository;

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
	
}
