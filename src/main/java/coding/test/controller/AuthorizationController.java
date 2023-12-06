package coding.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coding.test.entity.Member;
import coding.test.service.RegisterMemberService;



@RestController
@RequestMapping("/auth")
public class AuthorizationController {

	private final RegisterMemberService registerMemberService;

	public AuthorizationController(RegisterMemberService registerMemberService) {
		this.registerMemberService = registerMemberService;
	}

	// 회원가입 시 목록 추가할 곳
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody Member dto) {
		try {
			

			registerMemberService.join
			(dto.getEmail(), 
					dto.getPassword(),
					dto.getName(),
					dto.getPhone(),	
					dto.getRole(), 
					dto.getProvider()

			);
			System.err.println(dto.getEmail());

			return ResponseEntity.ok("가입 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
