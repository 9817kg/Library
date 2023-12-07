package coding.test.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;

import coding.test.ouath2.UserProfile;
import coding.test.repository.QuaryRepository;
import coding.test.service.BookService;
import coding.test.service.LoanService;
import coding.test.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Transactional
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MypageController {
	@Autowired
	private QuaryRepository query;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private LoanService loanService;
	
	private final BookService bookService;

	@GetMapping("/mypage")
	public String myPage(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			System.err.println(userProfile.getName());

		}
		System.err.println("UserProfile no");
		return "mypage";
	}
	
	
	@GetMapping("/myBook")
	public String myBook(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page) {
	    Object dtoObject = session.getAttribute("dto");

	    int pageSize = 6; // 페이지당 리뷰 수
	    int offset = (page - 1) * pageSize;
	    Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

        // 현재 페이지 번호와 상품 목록을 모델에 추가
        model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
        model.addAttribute("bookPage", bookPage);

	    if (dtoObject instanceof Member) {
	        Member dto = (Member) dtoObject;
	        List<Loan> myBook = loanService.getCategoryItemsWithPagination(dto.getId(), offset, pageSize);
	        model.addAttribute("dto", dto);
	        model.addAttribute("loanDatas", myBook);
	        session.setAttribute("dto", dto);
	    } else if (dtoObject instanceof UserProfile) {
	        UserProfile userProfile = (UserProfile) dtoObject;
	        List<Loan> myBook = loanService.getCategoryItemsWithPagination(userProfile.getId(), offset, pageSize);
	        model.addAttribute("dto", userProfile);
	        model.addAttribute("loanDatas", myBook);
	        session.setAttribute("dto", userProfile);
	        System.err.println(userProfile.getName());
	    }

	    return "myBook";
	}

	

	@GetMapping("/myInfo")
	public String myInfo(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);

		}
		return "myInfo";
	}

	@GetMapping("/changepw")
	public String changepw(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);

			session.setAttribute("dto", dto);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);

		}
		return "changepw";
	}

	@PostMapping("/changepw")
	public String changePw(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof Member) {
			Member dto = (Member) session.getAttribute("dto");

			// 현재 비밀번호와 저장된 비밀번호를 비교하여 일치하는지 확인
			if (passwordEncoder.matches(password, dto.getPassword())) {
				// 새 비밀번호와 확인 비밀번호가 일치하는지 확인
				if (newPassword.equals(confirmPassword)) {
					// 새 비밀번호를 암호화하여 저장
					String hashedNewPassword = passwordEncoder.encode(newPassword);

					// QuaryRepository를 통해 데이터베이스에 비밀번호 업데이트
					query.updatePassword(hashedNewPassword, dto.getPassword());

					// 업데이트된 엔터티를 다시 불러옴
					Member updatedMember = query.findById(dto.getId()).orElse(null);

					// 업데이트된 엔터티를 세션에 저장
					session.setAttribute("dto", updatedMember);

					// 비밀번호 변경 성공 시 로그아웃 후 메인 페이지로 리다이렉트
					redirectAttributes.addAttribute("message", "비밀번호변경완료");
					return "redirect:/login";
				} else {
					model.addAttribute("dto", dto);
					// 비밀번호가 일치하지 않는 경우 다시 변경 페이지로 이동
					return "changepw";
				}
			}
		}
		// 비밀번호 변경 실패 시 변경 페이지로 이동
		return "changepw";
	}

	@GetMapping("/withdraw")
	public String withdraw(Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);

		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);

		}
		return "withdraw";
	}

	@DeleteMapping("/withdraw/{memberId}")
	@ResponseBody
	public String deleteMember(@RequestParam("pw") String pw, @PathVariable Long memberId, Model model,
			HttpSession session) {
		try {
			// 회원을 삭제하는 비즈니스 로직 수행
			Member dto = (Member) session.getAttribute("dto");
			
			// 현재 비밀번호와 저장된 비밀번호를 비교하여 일치하는지 확인
			if (passwordEncoder.matches(pw, dto.getPassword())) {

				memberService.deleteMemberById(memberId);

			}
			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}
	}
	@GetMapping("/registBook")
	public String registBook(@RequestParam(name = "page", defaultValue = "1") int page, Model model,
	        HttpSession session) {
		int pageSize = 6;
		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);
		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);
	    Object dtoObject = session.getAttribute("dto");

	    if (dtoObject instanceof Member) {
	        Member dto = (Member) dtoObject;
	        System.err.println(dto.getName());
	        model.addAttribute("dto", dto);
	        session.setAttribute("dto", dto);

	        System.err.println(dto.getName());
	        Page<Book> bookList = bookService.getBooksByAuthor(dto.getName(), page, pageSize);
	        model.addAttribute("PageNumber", bookList.getNumber() + 1);
	        model.addAttribute("Page", bookList);
	        model.addAttribute("data", bookList.getContent());

	    } else if (dtoObject instanceof UserProfile) {
	        UserProfile userProfile = (UserProfile) dtoObject;
	        model.addAttribute("dto", userProfile);
	        session.setAttribute("dto", userProfile);

	        Page<Book> bookList = bookService.getBooksByAuthor(userProfile.getName(), page, pageSize);
	        model.addAttribute("PageNumber", bookList.getNumber() + 1);
	        model.addAttribute("Page", bookList);
	        model.addAttribute("data", bookList.getContent());
	    }

	    return "registBook";
	}


	

	@GetMapping("/modifyRegistBook/{bookId}")
	public String modifyRegistBook(@PathVariable Long bookId, Model model) {
		Optional<Book> book = bookService.getFindId(bookId);
		model.addAttribute("book", book);
		return "modifyRegistBook"; // 수정 페이지의 이름
	}

	@PostMapping("/updateRegistBook/{bookId}")
	public String updateRegistBook(@PathVariable Long bookId, @RequestParam("title") String title,
			@RequestParam("author") String author,@RequestParam("category") String category,@ModelAttribute Book book, Model model, @RequestParam("upFiles") MultipartFile imageFile) {
		Optional<Book> optionalNotice = bookService.getFindId(bookId);
		LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);
		if (optionalNotice.isPresent()) {
			String imageName = imageFile.getOriginalFilename();
			book = optionalNotice.get();
			book.setImg(imageName);
			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setDate(formattedDateTime);
			bookService.save(book);
			return "redirect:/my/registBook";
		}
		else {
			System.err.println("예외");
			return "redirect:/main";
		}
		 
	}
	
	@GetMapping("/loanMember/{bookItemcount}")
	public String showLoanUser(@PathVariable Long bookItemcount,Model model) {
		List<Loan> loans = loanService.getLoansByBookItemcount(bookItemcount);
        model.addAttribute("loans", loans);
        return "loanMember"; // 뷰의 이름을 자신의 프로젝트에 맞게 수정
	}

}
