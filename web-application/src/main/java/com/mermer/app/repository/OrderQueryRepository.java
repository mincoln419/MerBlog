package com.mermer.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

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

	private List<OrderItemQueryDto> findOrderitems(Long orderId) {
		return em.createQuery(
				"select new new com.mermer.app.api.dto.OrderItemQueryDto(o.order.id, i.name, oi.orderPrice, oi.count)"
						+ " from OrderItem oi" 
						+ " join oi.item i" 
						+ " where oi.order.id = :orderId",
				OrderItemQueryDto.class).setParameter("orderId", orderId).getResultList();
	}

	public List<OrderQueryDto> findOrders() {
		return em.createQuery(
				"select new com.mermer.app.api.dto.OrderQueryDto(o.id, m.name, o.orderDto, o.status, d.address) "
						+ "from Order o " 
						+ "join o.member m " 
						+ "join o.delivery d ",
				OrderQueryDto.class).getResultList();

	}

}
