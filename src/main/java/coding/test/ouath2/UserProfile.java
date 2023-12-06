package coding.test.ouath2;



import java.time.LocalDateTime;

import coding.test.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;






@Getter

@NoArgsConstructor
public class UserProfile {
    private long id;
    private String name; // 사용자 이름
    private String provider; // 로그인한 서비스
    private String email; // 사용자의 이메일
    // 사용자의 실제 이름
    private String birth; 
    private String birthDay; 
    private String birthYear; 
    private String phone; 
    private String memberType; 
    private String profile;
    private String career;
    private String introduce;
   
    private String role; 
    private String joinDate;
    
    public void setIntroduce(String introduce) {
	this.introduce = introduce;
    }
    public void setCareer(String career) {
	this.career = career;
    }
    
    public void setProfile(String profile) {
	this.profile = profile;
    }
   
    public void setId(long id) {
	this.id = id;
    }
    public void setJoinDate(String joinDate) {
	this.joinDate = joinDate;
    }
    public void setRole(String role) {
		this.role = role;
	}
    public void setMemberType(String memberType) {
	this.memberType = memberType;
    }
    public void setBirth(String birth) {
	this.birth = birth;
    }
    public void setMobile(String phone) {
	this.phone = phone;
    }
    public void setUserName(String userName) {
        this.name = userName;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setBirthDay(String birthDay) {
	this.birthDay = birthDay;
    }
    public void setBirthYear(String birthYear) {
	this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    // DTO 파일을 통하여 Entity를 생성하는 메소드
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .joinDate(this.joinDate)
                .phone(this.phone)
                .provider(this.provider)
                .role("USER")
                .build();
    }
}