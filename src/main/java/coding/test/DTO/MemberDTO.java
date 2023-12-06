package coding.test.DTO;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;

    private String memberType;
    private String phone;
    private String birth;
    private String introduce;
    private String career;
    private String profile;
    
    

    private String role;
    private String provider;

}
