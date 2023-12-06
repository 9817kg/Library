package coding.test.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

	private Long itemcount;
	private String category;
	private String code;
	private int loan;

	private String img;
	private String author;
	private String title;
	private String date;
	private String description;
}
