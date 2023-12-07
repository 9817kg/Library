package coding.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import coding.test.entity.Book;
import coding.test.entity.Loan;
import coding.test.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository repository;

	public List<Book> Category_item_All(String item) {

		return repository.findByCategory(item);

	}
	
	public List<Book> getBookByAuthor(String author) {
		return repository.findByCategory(author);
	}

	public Optional<Book> SelectONE(Long id) {
		Optional<Book> belt = repository.findById(id);
		// 나중에 형 변환
		return belt;

	}
    public List<Book> getCategoryItemsWithPagination(String category, int offset, int pageSize) {
        // 해당 카테고리의 상품을 페이지별로 가져오기
        PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize);
        Page<Book> bookPage = repository.findByCategory(category, pageRequest);
        return bookPage.getContent();
    }

	public Book getBookById(Long productId) {
		Optional<Book> productOptional = repository.findById(productId);
		return productOptional.orElse(null); // 해당 ID에 해당하는 제품이 없으면 null을 반환
	}

	public List<Book> getAllBookes() {
		return repository.findAll();
	}

	public Book save(Book notice) {

		return repository.save(notice);
	}

	public Page<Book> getBookByPage(int page, int size) {
		return repository.findAll(PageRequest.of(page - 1, size));

	}

	public Optional<Book> getFindId(Long id) {

		return repository.findById(id);
	}

	public void deleteBookById(Long id) {
		repository.deleteById(id);
	}
	public List<Book> getBooksWithPagination(int offset, int pageSize) {
	    // 해당 카테고리의 상품을 페이지별로 가져오기
	    PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize, Sort.by(Sort.Direction.DESC, "loan"));
	    Page<Book> bookPage = repository.getBookPaged(pageRequest);
	    
	    return bookPage.getContent(); // 이미 정렬된 결과를 반환
	}
	
	public List<Book> getBooksWithPage(int offset, int pageSize) {
	    // 해당 카테고리의 상품을 페이지별로 가져오기
	    PageRequest pageRequest = PageRequest.of(offset / pageSize, pageSize, Sort.by(Sort.Direction.DESC, "date"));
	    Page<Book> bookPage = repository.getBookPaged(pageRequest);
	    
	    return bookPage.getContent(); // 이미 정렬된 결과를 반환
	}



	 
	
	// 추가: 페이징 및 정렬 적용
    public Page<Book> getBooksByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "itemcount"));
        return repository.findAll(pageRequest);
    }

    // 추가: 작가별 페이징 및 정렬 적용
    public Page<Book> getBooksByAuthor(String author, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "itemcount"));
        return repository.findByAuthor(author, pageRequest);
    }

   

}
