package com.mermer.app.api.dto;

import java.time.LocalDateTime;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderFlatDto {
	private Long orderId;
	private String name;
	private LocalDateTime orderData;
	private OrderStatus orderStatus;
	private Address address;
	
	private String itemName;
	private int orderPrice;
	private int count;
}
