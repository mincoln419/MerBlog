/**
 * @packageName : com.mermer.app.domain.item
 * @fileName : ItemUpdateTest.java 
 * @author : Mermer 
 * @date : 2022.05.07 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.07 Mermer 최초 생성
 */
package com.mermer.app.domain.item;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/* 
 * @description: 
 */
@SpringBootTest
public class ItemUpdateTest {

	@Autowired
	EntityManager em;
	
	@Test
	public void updateTest() throws Exception{
		Book book = em.find(Book.class, 1L);
		
		
		//TX
		book.setName("asdfsdf");
		

		
		
		
	}
}
