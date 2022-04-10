/**
 * @packageName : com.mermer.app
 * @fileName : HelloController.java 
 * @author : Mermer 
 * @date : 2022.04.10 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.10 Mermer 최초 생성
 */
package com.mermer.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* 
 * @description: 
 */
@Controller
public class HelloController {

	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello");
		
		return "hello";
	}
}
