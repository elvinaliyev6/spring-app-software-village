package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.enums.EnumAvailableStatus;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import az.softwarevillage.book.exception.UserExistsException;
import az.softwarevillage.book.exception.UserNotFoundExeption;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BaseResponse registerBook(BookRequest bookRequest) {
        checkBookISBN(bookRequest);

        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .count(bookRequest.getCount())
                .publisher(bookRequest.getPublisher())
                .author(bookRequest.getAuthor())
                .isbn(bookRequest.getIsbn())
                .genre(bookRequest.getGenre())
                .status(EnumAvailableStatus.ACTIVE.getValue()).build();
        bookRepository.save(book);
        return BaseResponse.getSuccessMessage();
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAllByStatus(EnumAvailableStatus.ACTIVE.getValue());
        return getBookResponseList(books);
    }

    public BookResponse getBookById(Long id) {
        Book book = checkBook(id);
        return mapEntityToResponse(book);

    }

    private BookResponse mapEntityToResponse(Book book) {
        return BookResponse.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .publisher(book.getPublisher())
                .genre(book.getGenre())
                .isbn(book.getIsbn())
                .count(book.getCount())
                .status(book.getStatus())
                .build();
    }

    private Book checkBook(Long id) {
        Book book = bookRepository.findByIdAndStatus(id, EnumAvailableStatus.ACTIVE.getValue());

        if (book == null) {
            throw new UserNotFoundExeption(ErrorCodeEnum.USER_NOT_FOUND_ERROR.getMessage());
        }
        return book;
    }

    private List<BookResponse> getBookResponseList(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book b : books) {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setIsbn(b.getIsbn());
            bookResponse.setTitle(b.getTitle());
            bookResponse.setAuthor(b.getAuthor());
            bookResponse.setStatus(b.getStatus());
            bookResponse.setPublisher(b.getPublisher());
            bookResponse.setCount(b.getCount());
            bookResponse.setGenre(b.getGenre());

            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    private void checkBookISBN(BookRequest bookRequest) {
        List<BookResponse> books = getAllBooks();

        for (BookResponse b : books) {
            if (b.getIsbn().equals(bookRequest.getIsbn())) {
                throw new UserExistsException(ErrorCodeEnum.ISBN_ALREADY_EXISTS_ERROR.getMessage());
            }
        }
    }

    public BaseResponse updateUser(Long id, BookRequest bookRequest) {
        checkBook(id);
        checkBookISBN(bookRequest);
        Book book = bookRepository.findByIdAndStatus(id, EnumAvailableStatus.ACTIVE.getValue());
        if (book == null) {
            throw new UserNotFoundExeption(ErrorCodeEnum.USER_NOT_FOUND_ERROR.getMessage());
        }
        book.setTitle(bookRequest.getTitle());
        book.setCount(bookRequest.getCount());
        book.setGenre(bookRequest.getGenre());
        book.setPublisher(bookRequest.getPublisher());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setGenre(bookRequest.getGenre());
        book.setStatus(bookRequest.getStatus());
        bookRepository.save(book);
        return BaseResponse.getSuccessMessage();
    }
}
