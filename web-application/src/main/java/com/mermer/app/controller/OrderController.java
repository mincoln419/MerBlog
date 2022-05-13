/**
 * @packageName : com.mermer.app.controller
 * @fileName : OrderController.java 
 * @author : Mermer 
 * @date : 2022.05.07 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.07 Mermer 최초 생성
 */
package com.mermer.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mermer.app.domain.Member;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderSearch;
import com.mermer.app.domain.item.Item;
import com.mermer.app.service.ItemService;
import com.mermer.app.service.MemberService;
import com.mermer.app.service.OrderService;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order(
			@RequestParam("memberId") Long memberId, 
			@RequestParam("itemId") Long itemId,
			@RequestParam("count") int count
			) throws Exception {
		
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
		List<Order> orders =  orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";	
	}
	
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) throws Exception {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}
