package coding.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Builder
@Data

@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemcount;
    private String category;
    private String code;
    private String img;
    private String author;
    private String title;
    private String date;
    private String description;
    private int loan;
    private int dibs;
    
    public  Book() {
		this.loan=0;
		this.dibs=0;
	}

}