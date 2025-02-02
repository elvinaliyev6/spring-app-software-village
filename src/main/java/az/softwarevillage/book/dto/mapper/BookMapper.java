package az.softwarevillage.book.dto.mapper;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookRequest request);
    BookResponse toBookResponse(Book book);
}
