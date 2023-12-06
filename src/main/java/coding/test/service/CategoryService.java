package coding.test.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import coding.test.entity.Book;
import coding.test.repository.AddBookRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final AddBookRepository repository;

    public List<Book> getAllJavaEntities() {
        return repository.findAll();
    }
    
    public Book getJavaEntityById(Long id) {
        Optional<Book> optionalJavaEntity = repository.findByitemcount(id);
        return optionalJavaEntity.orElse(null);
    }
    public void saveJavaEntity(Book book) {
    	repository.save(book);
    }
 // 카테고리로 제품 목록 가져오기
    public List<Book> getBookByCategory(String category) {
        return repository.findByCategory(category);
    }

}


