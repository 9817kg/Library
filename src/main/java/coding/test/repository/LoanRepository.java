package coding.test.repository;

import java.util.List;

import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.entity.Member;

public interface LoanRepository extends JpaRepository<Loan, Long>{
	List<Loan> findByMemberAndReturnedFalse(Member member);
	Page<Loan> findByMemberId(Long memberId, Pageable pageable);
	 Loan findByBookAndMemberAndReturnedFalse(Book book, Member member);
	 Loan findByid(Long id);
	 
	List<Loan> findAllByMember(Long id);
	 List<Loan> findByBookItemcount(Long bookItemcount);
}
