package coding.test.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import coding.test.DTO.BookDto;
import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;

import coding.test.ouath2.UserProfile;
import coding.test.repository.BookRepository;
import coding.test.repository.LoanRepository;
import coding.test.repository.MemberQuery;
import coding.test.service.BookService;
import coding.test.service.CategoryService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
	BookDto bookDto = new BookDto();

	private final MemberQuery query;
	private final CategoryService categoryService;
	private final BookService bookService;
	private final LoanRepository loanRepository;

	

	@GetMapping(value = "/bookDetail/{bookId}")
	public String productDetail(@PathVariable Long bookId, Model model, HttpSession session) {
		Object dtoObject = session.getAttribute("dto");
		
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			bookDto.setItemcount(bookId);
			bookDto.setDate(query.selectDate(bookId));
			bookDto.setTitle(query.selectTitle(bookId));
			bookDto.setAuthor(query.selectAuthor(bookId));
			bookDto.setCode(query.selectCode(bookId));
			bookDto.setDescription(query.selectDescription(bookId));
			bookDto.setImg(query.selectImg(bookId));
			bookDto.setCategory(query.selectCategory(bookId));

			model.addAttribute("dto", dto);

			model.addAttribute("product", bookDto);

			return "bookDetail";
		}

		else if (dtoObject instanceof UserProfile) {

			UserProfile userProfile = (UserProfile) dtoObject;

			bookDto.setDate(query.selectDate(bookId));
			bookDto.setTitle(query.selectTitle(bookId));
			bookDto.setAuthor(query.selectAuthor(bookId));
			bookDto.setCode(query.selectCode(bookId));
			bookDto.setDescription(query.selectDescription(bookId));
			bookDto.setImg(query.selectImg(bookId));
			bookDto.setCategory(query.selectCategory(bookId));

			model.addAttribute("product", bookDto);
			model.addAttribute("dto", userProfile);

			return "bookDetail";

		} else {

			bookDto.setDate(query.selectDate(bookId));
			bookDto.setTitle(query.selectTitle(bookId));
			bookDto.setAuthor(query.selectAuthor(bookId));
			bookDto.setCode(query.selectCode(bookId));
			bookDto.setDescription(query.selectDescription(bookId));
			bookDto.setImg(query.selectImg(bookId));
			bookDto.setCategory(query.selectCategory(bookId));

			model.addAttribute("product", bookDto);
			return "bookDetail";
		}
	}

	@GetMapping("/myBook")
	public String myProduct(HttpSession session, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		Object dtoObject = session.getAttribute("dto");
		int pageSize = 6;
		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);
		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);

		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			List<Loan> myProduct = loanRepository.findAllByMember(dto.getId());
			model.addAttribute("list", myProduct);
			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "myBook";
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			List<Loan> myProduct = loanRepository.findAllByMember(userProfile.getId());
			model.addAttribute("list", myProduct);
			model.addAttribute("dto", userProfile);
			session.setAttribute("dto", userProfile);
			return "myBook";
		}
		return "myBook";
	}

	@GetMapping("/addBook")
	public String showAddBook(HttpSession session, Model model) {
		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;

			model.addAttribute("dto", dto);
			session.setAttribute("dto", dto);
			return "addBook";
		} else {
			return "addBook";}
		

		
	}

	@PostMapping("/addBook")
	public String addBook(@ModelAttribute Book book, Model model, @RequestParam("upFiles") MultipartFile imageFile) {
		LocalDateTime joinDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = joinDate.format(formatter);
		String imageName = imageFile.getOriginalFilename();
		book.setCode(generateRandomBookId());
		book.setDate(formattedDateTime);
		book.setImg(imageName);
		System.err.println(imageName);

		categoryService.saveJavaEntity(book);

		return "redirect:/main";
	}

	public static String generateRandomBookId() {
		// 랜덤한 UUID 생성
		UUID uuid = UUID.randomUUID();

		// 하이픈을 제거하고 소문자로 변환하여 반환
		String shortId = uuid.toString().replace("-", "").toLowerCase().substring(0, 8);
		return shortId;
	}

}
