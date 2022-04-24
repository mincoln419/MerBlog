/**
 * @packageName : com.mermer.app.domain
 * @fileName : OrderSearch.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.domain;

import javax.persistence.Entity;

import lombok.Data;

/* 
 * @description: 
 */
@Data
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus;
}
