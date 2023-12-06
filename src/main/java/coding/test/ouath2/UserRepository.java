package coding.test.ouath2;


import org.springframework.data.jpa.repository.JpaRepository;

import coding.test.entity.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findUserByEmailAndProvider(String email, String provider); 
}