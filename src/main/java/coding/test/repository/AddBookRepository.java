package coding.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coding.test.entity.Book;

public interface AddBookRepository extends JpaRepository<Book, Long> {
	Optional<Book> findBytitle(String provider);

	Optional<Book> findByitemcount(Long id);

	List<Book> findByCategory(String category);
}
