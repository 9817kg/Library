package coding.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import coding.test.entity.Member;
import coding.test.repository.MemberRepository;




@Service
public class RegisterMemberService {
	private final PasswordEncoder passwordEncoder;
	private final coding.test.repository.MemberRepository repository;

	@Autowired
	public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}

	public Long join(String email, String password, String name,String phone, String role, String provider

	) {

		Member member = Member.createMember(email, password, name,phone,passwordEncoder);
		validateDuplicateMember(member);
		repository.save(member);

		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		repository.findByEmail(member.getEmail()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
}
