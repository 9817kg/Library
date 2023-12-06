package coding.test.find;

import lombok.AllArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coding.test.repository.QuaryRepository;



@Controller
@AllArgsConstructor

public class MailController {
    private final UserService userService;
    private final SendEmailService sendEmailService;
    private final QuaryRepository quaryRepository;
    // Email과 name의 일치여부를 check하는 컨트롤러
    @GetMapping("/findPw")
    public String findPw() {

	return "findPw";
    }

    @PostMapping("/findPw")
    public String newPwPost(@RequestParam("userEmail") String email, @RequestParam("userName") String name,
	    RedirectAttributes attributes) {
	boolean pwFindCheck = userService.userEmailCheck(email, name);
	if (pwFindCheck) {
	    MailDTO dto = sendEmailService.createMailAndChangePassword(email, name);
	    sendEmailService.mailSend(dto);
	    attributes.addAttribute("message", "이메일전송");

	    return "redirect:/login";
	} else {
	    attributes.addAttribute("message", "이메일전송");
	    return "redirect:/findPw";
		}
    }
    
    @GetMapping("/newPw")
    public String newPw() {

	return "newPw";
    }
    
    
    @PostMapping("/newPw")
    public String findPwPost(@RequestParam("email") String email, @RequestParam("newPassword") String password,
	    RedirectAttributes attributes) {
    	if (quaryRepository.selectEmail(email).equals(email)) {
    		sendEmailService.updatePassword(password, email);
    		 return "redirect:/login";
		}else if(!quaryRepository.selectEmail(email).equals(email)){
			return "redirect:/findPw";
		}
    	return "redirect:/findPw";
    	
    }
}

	
