/**
 * @packageName : com.mermer.app.controller
 * @fileName : MemberForm.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/* 
 * @description: 
 */
@Data
public class MemberForm {

	@NotEmpty(message = "회원 이름은 필수입니다")
	private String name;
	
	private String city;
	
	private String street;
	
	private String zipcode;
}
