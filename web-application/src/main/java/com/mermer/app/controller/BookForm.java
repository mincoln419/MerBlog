/**
 * @packageName : com.mermer.app.controller
 * @fileName : BookForm.java 
 * @author : Mermer 
 * @date : 2022.05.02 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.02 Mermer 최초 생성
 */
package com.mermer.app.controller;

import lombok.Getter;
import lombok.Setter;

/* 
 * @description: 
 */
@Getter @Setter
public class BookForm {

	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	private String author;
	private String isbn;
	
}
