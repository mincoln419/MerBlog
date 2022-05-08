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

import com.mermer.app.domain.item.Book;
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
	
	@Transactional
	public Item updateItem(Long itemId, Book param) {
		//변경감지 = dirty checking
		//TX commit;
		
		//준영속 엔티티? - 영속성 컨텍스트가 더는 관리하지 않는 엔티티
		//dirty checking 을 하지 않는다
		//-> merge를 사용해서 TX 처리해야한다.
		
		//=> merge 에 대한 상세 코드라고 볼 수 있음
		//=> 다만 merge의 경우는 null로 값이 넘어올경우에 null로 update될 위험이 있다.
		//따라서 실무에서는 merege보다는 dirty checking을 사용하는 코드를 사용하는 것이 좋다.
		
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(param.getPrice());
		findItem.setName(param.getName());
		findItem.setStockQuantity(param.getStockQuantity());
		
		//영속성에 올려서 그대로 끝내면 tranactional 어노테이션에 의해 commit이 되고 자동으로 flush 된다.
		//이때 dirty checking이 발생해서 update 처리가 되는것
		return findItem;
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}
}

