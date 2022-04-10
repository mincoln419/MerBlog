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

	private String city;
	private String street;
	private String zipcode;
	
}
