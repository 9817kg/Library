package coding.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import coding.test.entity.Member;
import coding.test.repository.MemberRepository;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminAccountInitializer(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if admin account already exists
        if (!memberRepository.existsByEmail("Admin@Admin")) {
            Member adminMember = Member.createAdmin(passwordEncoder);
            memberRepository.save(adminMember);
            System.out.println("Admin account created successfully.");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}
