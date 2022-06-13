/**
 * @packageName : com.mermer.app.domain
 * @fileName : Book.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

/* 
 * @description: 
 */
@DiscriminatorValue("B")
@Data
@Entity
public class Book extends Item{

	private String author;	
	private String isbn;
}
