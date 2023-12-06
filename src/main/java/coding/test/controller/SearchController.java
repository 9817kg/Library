package coding.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coding.test.DTO.BookDto;
import coding.test.entity.Book;
import coding.test.entity.Member;
import coding.test.ouath2.UserProfile;
import coding.test.repository.SearchQuery;
import coding.test.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;





@Controller
@RequiredArgsConstructor
public class SearchController {
    private final SearchQuery searchQuery;
    private final BookService bookService;

    @GetMapping("/search")
    public String search(
            HttpSession session,
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page) {

        Object dtoObject = session.getAttribute("results");
        List<BookDto> bookDtos = (List<BookDto>) dtoObject;

        int pageSize = 6;
        Page<Book> bookPage = bookService.getBooksByPage(page, pageSize);

        model.addAttribute("bookPageNumber", bookPage.getNumber() + 1);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("results", bookDtos);

        Object dtoObjectUser = session.getAttribute("dto");
        if (dtoObjectUser instanceof Member) {
            Member dto = (Member) dtoObjectUser;
            model.addAttribute("dto", dto);
        } else if (dtoObjectUser instanceof UserProfile) {
            UserProfile userProfile = (UserProfile) dtoObjectUser;
            model.addAttribute("dto", userProfile);
        }
        return "search";
    }

    @PostMapping("/search")
    public String searchPost(
            @RequestParam("type") String type,
            @RequestParam("search") String search,
            Model model,
            HttpSession session) {

        List<BookDto> bookDtos = new ArrayList<>();
        List<String> categories, imgs, descriptions, titles, authors, dates, codes;
        List<Integer> loans;
        List<Long> itemcounts;

        switch (type) {
            case "title":
                categories = searchQuery.BookCategoryWithTitle(search);
                imgs = searchQuery.BookImageWithTitle(search);
                descriptions = searchQuery.BookDescriptionWithcategory(search);
                titles = searchQuery.BookTitleWithTitle(search);
                authors = searchQuery.BookAuthorWithTitle(search);
                dates = searchQuery.BookDateWithTitle(search);
                codes = searchQuery.BookCodeWithTitle(search);
                loans = searchQuery.BookLoanWithTitle(search);
                itemcounts = searchQuery.BookcountWithTitle(search);
                break;
            case "category":
                categories = searchQuery.BookCategoryWithcategory(search);
                imgs = searchQuery.BookImageWithcategory(search);
                descriptions = searchQuery.BookDescriptionWithcategory(search);
                titles = searchQuery.BookTitleWithcategory(search);
                authors = searchQuery.BookauthorWithcategory(search);
                dates = searchQuery.BookDateWithcategory(search);
                codes = searchQuery.BookCodeWithcategory(search);
                loans = searchQuery.BookLoanWithcategory(search);
                itemcounts = searchQuery.BookcountWithcategory(search);
                break;
            case "author":
                categories = searchQuery.BookCategoryWithauthor(search);
                imgs = searchQuery.BookImageWithcategory(search);
                descriptions = searchQuery.BookDescriptionWithauthor(search);
                titles = searchQuery.BookTitleWithauthor(search);
                authors = searchQuery.BookauthorWithauthor(search);
                dates = searchQuery.BookDateWithauthor(search);
                codes = searchQuery.BookCodeWithauthor(search);
                loans = searchQuery.BookLoanWithauthor(search);
                itemcounts = searchQuery.BookcountWithauthor(search);
                break;
            case "code":
                categories = searchQuery.BookCategoryWithcode(search);
                imgs = searchQuery.BookImageWithcode(search);
                descriptions = searchQuery.BookDescriptionWithcode(search);
                titles = searchQuery.BookTitleWithcode(search);
                authors = searchQuery.BookauthorWithcode(search);
                dates = searchQuery.BookDateWithcode(search);
                codes = searchQuery.BookCodeWithcode(search);
                loans = searchQuery.BookLoanWithcode(search);
                itemcounts = searchQuery.BookcountWithcode(search);
                break;
            default:
                // 예외 처리 또는 기본값 설정
                return "redirect:/search";
        }

        int size = titles.size();

        for (int i = 0; i < size; i++) {
            BookDto bookDto = new BookDto();
            bookDto.setCategory(i < categories.size() ? categories.get(i) : "");
            bookDto.setImg(i < imgs.size() ? imgs.get(i) : "");
            bookDto.setDescription(i < descriptions.size() ? descriptions.get(i) : "");
            bookDto.setCode(i < codes.size() ? codes.get(i) : "");
            bookDto.setLoan(i < loans.size() ? loans.get(i) : 0);
            bookDto.setTitle(i < titles.size() ? titles.get(i) : "");
            bookDto.setAuthor(i < authors.size() ? authors.get(i) : "");
            bookDto.setDate(i < dates.size() ? dates.get(i) : "");
            bookDto.setItemcount(i < itemcounts.size() ? itemcounts.get(i) : 0);

            bookDtos.add(bookDto);
        }

        model.addAttribute("results", bookDtos);
        session.setAttribute("results", bookDtos);
        // 검색 결과 페이지로 이동
        return "redirect:/search";
    }
}
