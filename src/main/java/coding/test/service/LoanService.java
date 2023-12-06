package coding.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import coding.test.entity.Book;
import coding.test.entity.Loan;

import coding.test.repository.LoanRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanService {
	private final LoanRepository loanRepository;
	
	public List<Loan> getAllLoan() {
		return loanRepository.findAll();
	}
	
	
	 public List<Loan> getCategoryItemsWithPagination(Long memberId, int offset, int pageSize) {
	        // 해당 카테고리의 상품을 페이지별로 가져오기
	        PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize);
	        Page<Loan> bookPage = loanRepository.findByMemberId(memberId,pageRequest);
	        return bookPage.getContent();
	    }
	 
	 public List<Loan> getLoansByBookItemcount(Long bookItemcount) {
	        return loanRepository.findByBookItemcount(bookItemcount);
	    }
	
}
