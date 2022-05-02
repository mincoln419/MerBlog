/**
 * @packageName : com.mermer.app.controller
 * @fileName : ItemController.java 
 * @author : Mermer 
 * @date : 2022.05.02 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.02 Mermer 최초 생성
 */
package com.mermer.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mermer.app.domain.item.Book;
import com.mermer.app.service.ItemService;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping("/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}
	
	@PostMapping("/items/new")
	public String create(BookForm form) {
		Book book = new Book();
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQauntity(form.getStockQantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		
		itemService.saveItem(book);
		return "redirect:/";
	}
	
	
}
