package coding.test.DTO;

import java.time.LocalDateTime;

import coding.test.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDTO {

   private Long rno;
   private String reviewer;
   private String text;
   private String password;
   private String content;
   
   private Book book;
   
   private Long itemcount;
   private LocalDateTime regDate, modDate;
}
