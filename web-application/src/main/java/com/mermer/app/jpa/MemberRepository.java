/**
 * @packageName : com.mermer.app.jpa
 * @fileName : MemberRepository.java 
 * @author : Mermer 
 * @date : 2022.04.16 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.16 Mermer 최초 생성
 */
package com.mermer.app.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mermer.app.domain.Member;

/* 
 * @description: 
 */
public interface MemberRepository extends JpaRepository<Member, Long>{

}
