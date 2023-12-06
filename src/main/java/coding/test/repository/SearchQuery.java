package coding.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coding.test.entity.Book;



public interface SearchQuery extends JpaRepository<Book, String> {
    // 제목으로 찾기
    @Query(value = "SELECT category FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookCategoryWithTitle(@Param("search") String search);
    @Query(value = "SELECT description FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookDescriptionWithTitle(@Param("search") String search);
    @Query(value = "SELECT img FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookImageWithTitle(@Param("search") String search);
    @Query(value = "SELECT author FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookAuthorWithTitle(@Param("search") String search);
    @Query(value = "SELECT title FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookTitleWithTitle(@Param("search") String search);
    @Query(value = "SELECT date FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookDateWithTitle(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<Long> BookcountWithTitle(@Param("search") String search);
    @Query(value = "SELECT code FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<String> BookCodeWithTitle(@Param("search") String search);
    @Query(value = "SELECT loan FROM Book p WHERE p.title LIKE %:search%", nativeQuery = true)
    List<Integer> BookLoanWithTitle(@Param("search") String search);
    
    // 카테고리로 찾기
    @Query(value = "SELECT category FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookCategoryWithcategory(@Param("search") String search);
    @Query(value = "SELECT description FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookDescriptionWithcategory(@Param("search") String search);
    @Query(value = "SELECT img FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookImageWithcategory(@Param("search") String search);
    @Query(value = "SELECT price FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookPriceWithcategory(@Param("search") String search);
    @Query(value = "SELECT author FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookauthorWithcategory(@Param("search") String search);
    @Query(value = "SELECT title FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookTitleWithcategory(@Param("search") String search);
    @Query(value = "SELECT date FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
    List<String> BookDateWithcategory(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Book p WHERE p.category LIKE %:search%", nativeQuery = true)
     List<Long> BookcountWithcategory(@Param("search") String search);
    @Query(value = "SELECT code FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookCodeWithcategory(@Param("search") String search);
    @Query(value = "SELECT loan FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<Integer> BookLoanWithcategory(@Param("search") String search);
    
    
    
    
    
    //저자로 찾기
    @Query(value = "SELECT category FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookCategoryWithauthor(@Param("search") String search);
    @Query(value = "SELECT description FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookDescriptionWithauthor(@Param("search") String search);
    @Query(value = "SELECT img FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookImageWithauthor(@Param("search") String search);
    
    @Query(value = "SELECT author FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookauthorWithauthor(@Param("search") String search);
    @Query(value = "SELECT title FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookTitleWithauthor(@Param("search") String search);
    @Query(value = "SELECT date FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookDateWithauthor(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<Long> BookcountWithauthor(@Param("search") String search);
    @Query(value = "SELECT code FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<String> BookCodeWithauthor(@Param("search") String search);
    @Query(value = "SELECT loan FROM Book p WHERE p.author LIKE %:search%", nativeQuery = true)
    List<Integer> BookLoanWithauthor(@Param("search") String search);
    
  //책코드로 찾기
    
    @Query(value = "SELECT category FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookCategoryWithcode(@Param("search") String search);
    @Query(value = "SELECT description FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookDescriptionWithcode(@Param("search") String search);
    @Query(value = "SELECT img FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookImageWithcode(@Param("search") String search);
    
    @Query(value = "SELECT author FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookauthorWithcode(@Param("search") String search);
    @Query(value = "SELECT title FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookTitleWithcode(@Param("search") String search);
    @Query(value = "SELECT date FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookDateWithcode(@Param("search") String search);
    @Query(value = "SELECT itemcount FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<Long> BookcountWithcode(@Param("search") String search);
    @Query(value = "SELECT code FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<String> BookCodeWithcode(@Param("search") String search);
    @Query(value = "SELECT loan FROM Book p WHERE p.code LIKE %:search%", nativeQuery = true)
    List<Integer> BookLoanWithcode(@Param("search") String search);

}
