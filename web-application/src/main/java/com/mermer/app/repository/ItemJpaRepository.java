package com.mermer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mermer.app.domain.item.Book;
import com.mermer.app.domain.item.Item;

public interface ItemJpaRepository extends JpaRepository<Item, Long>{

}
