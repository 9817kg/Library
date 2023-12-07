package coding.test.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coding.test.entity.Book;
import coding.test.entity.Member;

import coding.test.ouath2.UserProfile;
import coding.test.service.BookService;
import coding.test.service.CategoryService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

	private final BookService bookService;

	@GetMapping("/fiction")
	public String fiction(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page) {
		// 페이지당 리뷰 수
		int pageSize = 6;

		// 페이지 번호와 페이지 크기를 기반으로 오프셋 계산
		int offset = (page - 1) * pageSize;

		// 서비스를 통해 특정 카테고리의 상품 목록을 페이징하여 가져옴
		List<Book> book = bookService.getCategoryItemsWithPagination("fiction", offset, pageSize);

		// 상품 목록을 모델에 추가
		model.addAttribute("book", book);

		// 전체 페이지 수를 고려하여 페이지 관리를 위한 Book Page 객체를 가져옴
		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

		// 현재 페이지 번호와 상품 목록을 모델에 추가
		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);

		// 세션에서 사용자 정보를 가져옴
		Object dtoObject = session.getAttribute("dto");

		// 사용자 정보에 따라 모델에 추가
		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
		}

		// 뷰 페이지로 이동
		return "/fiction";
	}

	@GetMapping("/knowledge")
	public String knowledge(Model model, HttpSession session,
			@RequestParam(name = "page", defaultValue = "1") int page) {

		int pageSize = 6;
		int offset = (page - 1) * pageSize;
		List<Book> book = bookService.getCategoryItemsWithPagination("knowledge", offset, pageSize);
		model.addAttribute("book", book);

		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);

		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
		}
		return "/knowledge";
	}

	@GetMapping("/hobby")
	public String hobby(Model model, HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page) {

		int pageSize = 6;

		int offset = (page - 1) * pageSize;

		List<Book> book = bookService.getCategoryItemsWithPagination("hobby", offset, pageSize);

		model.addAttribute("book", book);

		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);

		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
		}
		return "/hobby";
	}

	@GetMapping("/scholarship")
	public String scholarship(Model model, HttpSession session,
			@RequestParam(name = "page", defaultValue = "1") int page) {

		int pageSize = 6;

		int offset = (page - 1) * pageSize;

		List<Book> book = bookService.getCategoryItemsWithPagination("scholarship", offset, pageSize);

		model.addAttribute("book", book);

		Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

		model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
		model.addAttribute("bookPage", bookPage);

		Object dtoObject = session.getAttribute("dto");

		if (dtoObject instanceof Member) {
			Member dto = (Member) dtoObject;
			model.addAttribute("dto", dto);
		} else if (dtoObject instanceof UserProfile) {
			UserProfile userProfile = (UserProfile) dtoObject;
			model.addAttribute("dto", userProfile);
		}
		return "/scholarship";
	}

}
