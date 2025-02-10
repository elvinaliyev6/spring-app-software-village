package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.mapper.BookMapper;
import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookMapper bookMapper;

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    private Book book;
    private BookRequest bookRequest;

    @BeforeEach
    void setUp() {
        Book book=new Book();
        book.setIsbn("123");
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setCount(100);

        this.bookRequest= BookRequest.builder()
                .count(10)
                .genre("genre")
                .author("author")
                .build();

        this.book=book;
    }

    @Test
    void borrowBookWhenCountIsZero() {

        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(book));

        BaseResponse baseResponse = bookService.borrowBook(12L);
        assertNotNull(baseResponse);
        assertEquals("Process success compiled",baseResponse.getMessage());
        assertEquals(0, baseResponse.getCode());
    }

    @Test
    void addBookTest() {

        when(bookMapper.toBook(any())).thenReturn(book);

        BaseResponse baseResponse=bookService.addBook(bookRequest);
        assertNotNull(baseResponse);
        assertEquals("Process success compiled",baseResponse.getMessage());
        assertEquals(0, baseResponse.getCode());
    }

    @Test
    void getBookById() {

    }
}