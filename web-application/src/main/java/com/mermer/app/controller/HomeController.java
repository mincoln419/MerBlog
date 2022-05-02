/**
 * @packageName : com.mermer.app.controller
 * @fileName : HomeController.java 
 * @author : Mermer 
 * @date : 2022.04.24 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.24 Mermer 최초 생성
 */
package com.mermer.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/* 
 * @description: 
 */
@Controller
@Slf4j
public class HomeController {

	@RequestMapping("/")
	public String home() {
		log.info(">>home Controller");
		return "home";
	}
}
