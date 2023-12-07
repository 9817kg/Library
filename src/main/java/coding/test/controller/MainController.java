package coding.test.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coding.test.DTO.MemberDTO;
import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;
import coding.test.ouath2.OAuth2Service;
import coding.test.ouath2.UserProfile;
import coding.test.repository.MemberRepository;
import coding.test.service.BookService;
import coding.test.service.MemberService;
import jakarta.servlet.http.HttpSession;




@Controller
public class MainController {
	
	@Autowired
	private MemberRepository memberRepository; // OAuth2Service 주입 추가
	MemberDTO dto = new MemberDTO();

	Member member = new Member();
	@Autowired
	private	MemberService memberService;
	@Autowired
	private	BookService bookService;

	@GetMapping("/main")
	public String dashboardPage(@AuthenticationPrincipal User user, Model model, HttpSession session) {
		
		if (user != null) {
			
			String email = user.getUsername();
			Optional<Member> optionalMember = memberRepository.findByEmail(email);
			 if (optionalMember.isPresent()) {
		            Member dto = optionalMember.get();
		            session.setAttribute("dto", dto);
		            model.addAttribute("dto", dto);
		        }
			
		}else if(user == null) {
			
			UserProfile userProfile = (UserProfile) session.getAttribute("dto");
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
		}

		return "main";

	}
	
	@GetMapping("/")
	public String getIntro() {
		return "intro";
	}
	@GetMapping("/new")
	public String getNew(Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
		int pageSize = 6;
		int offset = (page - 1) * pageSize;
		
        Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);
        List<Book> books = bookService.getBooksWithPage(offset, pageSize);
       
        model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);
        
        model.addAttribute("book", books);

	    return "new";
	}

	@GetMapping("/famous")
	public String getFamous(Model model,@RequestParam(name = "page", defaultValue = "1") int page) {
        
		int pageSize = 6;
		int offset = (page - 1) * pageSize;
		
        Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);
        List<Book> books = bookService.getBooksWithPagination(offset, pageSize);
       
        model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);
        
        model.addAttribute("book", books);


	    return "famous";
	}
	
	
	
	
	
}

