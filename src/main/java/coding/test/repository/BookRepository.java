package coding.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import coding.test.entity.Book;
import coding.test.entity.Loan;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findAllByCategory(String category);

    List<Book> findByCategory(String item);
    
    @Query("SELECT b FROM Book b") // JPQL 쿼리 수정: 엔티티 Review를 반환하도록 변경
	 Page<Book> getBookPaged(Pageable pageable);
    Page<Book> findByCategory(String category, Pageable pageable);
    
    Page<Book> findByAuthor(String author, Pageable pageable);
   
  
    
   
}


