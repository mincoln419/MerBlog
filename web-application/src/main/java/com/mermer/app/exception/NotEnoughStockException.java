/**
 * @packageName : com.mermer.app.exception
 * @fileName : NotEnoughStockException.java 
 * @author : Mermer 
 * @date : 2022.04.23 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.23 Mermer 최초 생성
 */
package com.mermer.app.exception;

/* 
 * @description: 
 */
public class NotEnoughStockException extends RuntimeException {
	
	/**
	 * default excpetion 
	 */
	public NotEnoughStockException() {
		super();
	}
	
	/**
	 * message exception
	 */
	public NotEnoughStockException(String message) {
		super(message);
	}
	
	
}
