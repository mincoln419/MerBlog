/**
 * @packageName : com.mermer.app.controller
 * @fileName : MemberController.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Member;
import com.mermer.app.service.MemberService;

import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(@Valid MemberForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "members/createMemberForm";
		}
		
		Address address = Address.builder().city(form.getCity())
				 .street(form.getStreet())
				 .zipcode(form.getZipcode())
				 .build();
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);
		
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		 List<Member> members = memberService.findMembers();
		 model.addAttribute("members", members);
		 return "members/memberList";
	}
}
