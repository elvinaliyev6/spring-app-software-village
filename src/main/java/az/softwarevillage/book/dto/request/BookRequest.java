package az.softwarevillage.book.dto.request;

import lombok.Data;

@Data
public class BookRequest {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String genre;
    private int count;

    private Integer status;
}
