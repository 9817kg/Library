package coding.test.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
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

import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;
import coding.test.ouath2.UserProfile;
import coding.test.repository.MemberQuery;
import coding.test.service.BookService;
import coding.test.service.CategoryService;
import coding.test.service.LoanService;
import coding.test.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final CategoryService categoryService;
	private final BookService bookService;
	private final MemberService memberService;
	private final LoanService loanService;

	@GetMapping("/admin")
	public String collection() {

		return "admin";
	}
	
	@GetMapping("/loan-list")
	public String loan_list(Model model) {
		List<Loan> loanDatas = loanService.getAllLoan();
		model.addAttribute("loanDatas", loanDatas);
		return "loan-list";
		
	}
	
	
	@GetMapping("/returned_list")
	public String returned_list(Model model) {
		List<Loan> loanDatas = loanService.getAllLoan();
		model.addAttribute("loanDatas", loanDatas);
		return "returned_list";
		
	}

	@PostMapping("/admin")
	public String adminPost(@RequestParam("title") String title, @RequestParam("content") String content) {

		return "redirect:/admin/admin";
	}

	

	@GetMapping("/book_manage")
	public String notice_manager(@RequestParam(name = "page", defaultValue = "1") int page, Model model,
			HttpSession session) {
		int pageSize = 6; // 페이지당 리뷰 수

		List<Book> noticeList = bookService.getAllBookes();
		Page<Book> noticePage = bookService.getBookByPage(page, pageSize);
		model.addAttribute("PageNumber", noticePage.getNumber() + 1);
		model.addAttribute("Page", noticePage);

		model.addAttribute("data", noticeList);

		return "book_manage";

	}

	

	@GetMapping("/modifyBook/{bookId}")
	public String modifyNotice(@PathVariable Long bookId, Model model) {
		Optional<Book> book = bookService.getFindId(bookId);
		model.addAttribute("book", book);
		return "modifyBook"; // 수정 페이지의 이름
	}

	@PostMapping("/updateBook/{bookId}")
	public String updateNotice(@PathVariable Long bookId, @RequestParam("title") String title,
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
		}

		return "redirect:/admin/book_manage"; // 수정 후, 공지사항 관리 페이지로 이동
	}
	@GetMapping("/user_list")
	public String user_list(Model model) {
		// 전체 회원 목록을 가져온다
		List<Member> members = memberService.getAllMembers();
		model.addAttribute("members", members);
		return "user_list";
	}

	
	
}
