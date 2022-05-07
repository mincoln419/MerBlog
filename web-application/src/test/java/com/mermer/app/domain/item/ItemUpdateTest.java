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
		
		//변경감지 = dirty checking
		//TX commit;
		
		//준영속 엔티티? - 영속성 컨텍스트가 더는 관리하지 않는 엔티티
		//dirty checking 을 하지 않는다
		//-> merge를 사용해서 TX 처리해야한다.
		
		
		
	}
}
