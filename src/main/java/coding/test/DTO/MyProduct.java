package coding.test.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyProduct {
	String category;
	String count;
	String date;
	String description;
	String img;
	String provider;
	String title;
}
