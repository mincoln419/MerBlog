/**
 * @packageName : com.mermer.api
 * @fileName : MeberApiController.java 
 * @author : Mermer 
 * @date : 2022.05.14 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.05.14 Mermer 최초 생성
 */
package com.mermer.app.api;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mermer.app.domain.Member;
import com.mermer.app.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/* 
 * @description: 
 */
@RestController
@RequiredArgsConstructor
public class MeberApiController {

	private final MemberService memberService;
	
	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	@PostMapping("/api/v2/members")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
		
		Member member = new Member();
		member.setName(request.getName());
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	@PutMapping("/api/v2/members/{memberId}")
	public UpdateMemberResponse updateMemberV2(@PathVariable Long memberId, @RequestBody @Valid UpdateMemberRequest request) {
		memberService.update(memberId, request.getName());
		Member findMember = memberService.findOne(memberId);
		return new UpdateMemberResponse(memberId, findMember.getName());
	}
	
	@Data
	static class CreateMemberRequest{
		@NotEmpty
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	static class CreateMemberResponse{
		private Long id;
	}
	
	@Data
	static class UpdateMemberRequest{
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	static class UpdateMemberResponse{
		private Long id;
		private String name;
	}
	
	
	
	
}
