package com.mermer.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mermer.app.domain.item.Book;

@SpringBootTest
class ItemJpaRepositoryTest {

	@Autowired
	ItemJpaRepository itemJpaRepository;
	
	@Test
	void test() {
		Book book = new Book("1");
		itemJpaRepository.save(book);
	}

}
