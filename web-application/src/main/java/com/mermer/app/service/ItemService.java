/**
 * @packageName : com.mermer.app.service
 * @fileName : ItemService.java 
 * @author : Mermer 
 * @date : 2022.04.23 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.23 Mermer 최초 생성
 */
package com.mermer.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mermer.app.domain.item.Item;
import com.mermer.app.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}
