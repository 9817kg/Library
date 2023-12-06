package coding.test.find;

import lombok.AllArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "rywlswkd@naver.com";

    public void mailSend(MailDTO mailDto) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setTo(mailDto.getAddress());
	message.setFrom(MailService.FROM_ADDRESS);
	message.setSubject(mailDto.getTitle());
	message.setText(mailDto.getMessage());

	mailSender.send(message);
    }
}