package coding.test.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResultDto {
    String category;
    String description;
    String img;
    String price;
    String provider;
    String title;
    String date;
    Long itemcount; 
         
}
