package com.mermer.app.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.mermer.app.api.dto.OrderFlatDto;
import com.mermer.app.api.dto.OrderItemQueryDto;
import com.mermer.app.api.dto.OrderQueryDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

	private final EntityManager em;

	public List<OrderQueryDto> findOrderQueryDtos() {
		List<OrderQueryDto> result = findOrders();
		result.forEach(o -> {
			List<OrderItemQueryDto> orderItems = findOrderitems(o.getOrderId());
			o.setOrderItems(orderItems);
		});
		return result;
	}
	
	public List<OrderQueryDto> findAllByDto_optimization() {
		List<OrderQueryDto> result = findOrders();
		
		List<Long> orderIds = result.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
		
		List<OrderItemQueryDto> orderItems = em.createQuery(
				"select new com.mermer.app.api.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
						+ " from OrderItem oi" 
						+ " join oi.item i" 
						+ " where oi.order.id in :orderIds",
				OrderItemQueryDto.class).setParameter("orderIds", orderIds).getResultList();
		
		Map<Object, List<OrderItemQueryDto>> orderItemMap = orderItems.stream().collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));
		result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
		return result;
	}

	private List<OrderItemQueryDto> findOrderitems(Long orderId) {
		return em.createQuery(
				"select new com.mermer.app.api.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
						+ " from OrderItem oi" 
						+ " join oi.item i" 
						+ " where oi.order.id = :orderId",
				OrderItemQueryDto.class).setParameter("orderId", orderId).getResultList();
	}

	public List<OrderQueryDto> findOrders() {
		return em.createQuery(
				"select new com.mermer.app.api.dto.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) "
						+ "from Order o " 
						+ "join o.member m " 
						+ "join o.delivery d ",
				OrderQueryDto.class).getResultList();

	}

	public List<OrderFlatDto> findAllByDto_flat() {

		return em.createQuery("select new"
				+ " com.mermer.app.api.dto.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count) "
				+ " from Order o"
				+ " join o.member m"
				+ " join o.delivery d"
				+ " join o.orderItems oi"
				+ " join oi.item i", OrderFlatDto.class)
		.getResultList();
	}



}
