package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.mapper.BookMapper;
import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BaseResponse addBook(BookRequest bookRequest) {
        bookRepository.save(bookMapper.toBook(bookRequest));
        return BaseResponse.getSuccessMessage();
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : books) {
            bookResponses.add(bookMapper.toBookResponse(book));
        }
        return bookResponses;
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).get();
        return bookMapper.toBookResponse(book);
    }

    public BaseResponse updateBook(Long Id, BookRequest bookRequest) {
        Book book = bookRepository.findById(Id).get();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setCount(bookRequest.getCount());
        book.setGenre(bookRequest.getGenre());
        book.setPublisher(bookRequest.getPublisher());
        bookRepository.save(book);
        return BaseResponse.getSuccessMessage();
    }

    public BaseResponse deleteBook(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setStatus(0);
        bookRepository.save(book);
        return BaseResponse.getSuccessMessage();
    }

    public BaseResponse borrowBook(Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getCount() >= 1 && book.getStatus() == 1) {
            book.setCount(book.getCount() - 1);
            bookRepository.save(book);
            return BaseResponse.getSuccessMessage();
        } else {
            book.setStatus(0);
            bookRepository.save(book);
            return BaseResponse.builder().code(10).message("Book is not available").timestamp(LocalDateTime.now()).build();
        }
    }

    public BaseResponse returnBook(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setCount(book.getCount() + 1);
        if (book.getStatus() == 0) {
            book.setStatus(1);
        }
        bookRepository.save(book);
        return BaseResponse.getSuccessMessage();
    }
}
