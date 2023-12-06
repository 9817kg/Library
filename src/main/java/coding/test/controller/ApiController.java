package coding.test.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;
import coding.test.repository.LoanRepository;
import coding.test.service.BookService;
import coding.test.service.CategoryService;
import coding.test.service.LoanService;
import coding.test.service.MemberService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

	private final BookService bookService;
	private final MemberService memberService;
	private final LoanRepository loanRepository;
	private final LoanService loanService;
	
	@DeleteMapping("/deleteBook/{bookId}")
	@ResponseBody
	public String deleteNotice(@PathVariable Long bookId) {
		try {

			bookService.deleteBookById(bookId);
			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}
	}

	@DeleteMapping("/deleteMember/{memberId}")
	@ResponseBody
	public String deleteMember(@PathVariable Long memberId) {
		try {
			// 회원을 삭제하는 비즈니스 로직 수행
			memberService.deleteMemberById(memberId);
			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}
	}

	@PostMapping("/loanBook/{bookId}/{dtoId}")
	@ResponseBody
	public String loanBook(@PathVariable Long bookId, @PathVariable Long dtoId) {
		try {

			Optional<Book> bookOptional = bookService.getFindId(bookId);
			Optional<Member> memberOptional = memberService.findByMemberId(dtoId);
			if (memberOptional.isPresent() && bookOptional.isPresent()) {
				Member member = memberOptional.get();
				Book book = bookOptional.get();
				Loan loan = new Loan();
				loan.setMember(member);
				loan.setBook(book);

				LocalDate currentDate = LocalDate.now();
				LocalDate dueDate = currentDate.plusDays(30);
				loan.setLoanDate(currentDate);
				loan.setDueDate(dueDate);
				book.setLoan(book.getLoan() + 1);
				loan.setReturned(false);
				loanRepository.save(loan);
			}

			return "success";
		} catch (Exception e) {
			System.err.println("삭제 중 예외 발생 : " + e.getMessage());
			return "error";
		}

	}
	@PostMapping("/returnedBook/{productItemcount}/{dtoId}")
	@ResponseBody
	public String returnedBook(@PathVariable Long productItemcount, @PathVariable Long dtoId) {
	    try {
	        Optional<Book> bookOptional = bookService.getFindId(productItemcount);
	        Optional<Member> memberOptional = memberService.findByMemberId(dtoId);
	        
	        if (memberOptional.isPresent() && bookOptional.isPresent()) {
	            Book book = bookOptional.get();
	            Member member = memberOptional.get();

	            // 해당 도서의 대출 정보를 가져옴
	            Loan loan = loanRepository.findByBookAndMemberAndReturnedFalse(book, member);
	            
	            if (loan != null) {
	                LocalDate currentDate = LocalDate.now();
	                loan.setReturnedDate(currentDate);
	                book.setLoan(book.getLoan() - 1);
	                loan.setReturned(true);
	                loanRepository.save(loan);
	                
	                
	            } else {
	                // 대출 정보를 찾지 못한 경우 처리
	                return "error";
	            }
	        }
	        return "success";
	    } catch (Exception e) {
	        System.err.println("반납 중 예외 발생 : " + e.getMessage());
	        return "error";
	    }
	}
	
	
	

}
