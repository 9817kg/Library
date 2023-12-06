package coding.test.DTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ReviewPageResultDTO<DTO, EN> {

    private List<DTO> dtoList;

    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    public ReviewPageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        this.totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int totalPages = totalPage;

        int offset = 5; // 변경된 부분: 페이지가 현재 페이지 기준으로 양 옆으로 5개씩 보이도록 설정

        start = Math.max(1, page - offset);
        prev = start > 1;

        end = Math.min(start + 2 * offset, totalPages);
        next = totalPages > end;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    public List<DTO> getDtoList() {
        return this.dtoList;
    }

    public boolean hasPrevious() {
        return page > 1;
    }

    public boolean hasNext() {
        return page < totalPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }
}
