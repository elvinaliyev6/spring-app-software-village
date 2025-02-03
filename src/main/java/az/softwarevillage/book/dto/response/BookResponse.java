package az.softwarevillage.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String genre;
    private int count;
    private Integer status;
}
