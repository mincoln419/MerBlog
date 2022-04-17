/**
 * @packageName : com.mermer.app.domain
 * @fileName : Address.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

/* 
 * @description: 
 */
@Embeddable
@Getter
public class Address {

	
	protected Address() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param city
	 * @param street
	 * @param zipcode
	 */
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}

	
	private String city;
	private String street;
	private String zipcode;
	
}
