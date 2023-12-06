package coding.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coding.test.entity.Member;
import coding.test.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
	private final MemberRepository repository;

	@Autowired
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	public Optional<Member> findOne(String userId) {
		return repository.findByEmail(userId);
	}

	public List<Member> getAllMembers() {
		return repository.findAll();
	}

	public void deleteMemberById(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Member> findByMemberId(Long id) {
		return repository.findById(id);
	}

	
}
