package coding.test.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import coding.test.repository.MemberRepository;
import coding.test.repository.QuaryRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class SendEmailService {

    @Autowired
    MemberRepository memberRepository;

    private JavaMailSender mailSender;
    private PasswordEncoder passwordEncoder;
    private final QuaryRepository quary;
    private static final String FROM_ADDRESS = "rywlswkd@naver.com";
    private static final String link = "http://localhost/newPw";

    public MailDTO createMailAndChangePassword(String userEmail, String userName) {
        String str = getTempPassword();
        MailDTO dto = new MailDTO();
        dto.setAddress(userEmail);
        dto.setTitle("Hi " + userName + ", Change password of 9817Kg Travel.");

        // 이미지를 Base64로 인코딩하여 HTML에 포함
        String imageContent = getImageAsBase64("/static/images/join.jpg");
        String message = "<html><body style= border: 1px solid black; border-radius: 25px;  padding: 10px;>";
        message += "<h1> 9817kg Travel</h1>";
        message += "<h2> Hi!!!. 9817Kg Travel 비밀번호 안내입니다.</h2>";
        message += "<h3>[" + userName + "] 님, 해당 링크에서 비밀번호를 변경해주세요.</h3>";
        message += "<a href='" + link + "' style='    border: 1px solid black; border-radius: 25px; color: black; text-decoration: none; display: inline-block; padding: 10px; font-weight: bolder; transition: background-color 1s, padding 1s;'>"
                + "setting Password" + "</a>";
        message += "</body></html>";

        dto.setMessage(message);
        return dto;
    }

    public void updatePassword(String str, String userEmail) {
        String pw = passwordEncoder.encode(str);
        String email = memberRepository.findByEmail(userEmail).get().getEmail();
        quary.updateUserPassword(email, pw);
    }

    public String getTempPassword() {
        char[] charSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            str.append(charSet[idx]);
        }
        return str.toString();
    }

    public void mailSend(MailDTO mailDto) {
        System.out.println("이메일 전송 완료!");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(mailDto.getAddress());
            helper.setFrom(SendEmailService.FROM_ADDRESS);
            helper.setSubject(mailDto.getTitle());
            helper.setText(mailDto.getMessage(), true); // true: HTML 형식으로 설정

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getImageAsBase64(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            byte[] imageBytes = Files.readAllBytes(path);
            return java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
