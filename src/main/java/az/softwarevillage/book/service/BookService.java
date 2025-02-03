package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import az.softwarevillage.book.exception.AuthorNotFoundException;
import az.softwarevillage.book.exception.BookAlreadyExist;
import az.softwarevillage.book.exception.BookNotFoundException;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(BookRequest bookRequest) {
        Book book = new Book();

        BeanUtils.copyProperties(bookRequest, book);

        List<Book> books = bookRepository.findAll();
        for (Book book1 : books) {
            if (book1.getTitle().equals(bookRequest.getTitle())) {
                throw new BookAlreadyExist(ErrorCodeEnum.BOOK_ALREADY_EXIST_ERROR.getMessage());
            }
        }

        bookRepository.save(book);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book book : books) {
            bookResponses.add(mapBookToResponse(book));
        }

        return bookResponses;
    }

    public BookResponse getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            Book book1 = book.get();
            return mapBookToResponse(book1);
        } else {
            throw new BookNotFoundException(ErrorCodeEnum.BOOK_NOT_FOUND_ERROR.getMessage());
        }
    }

    public List<BookResponse> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);

        if (bookRepository.existsByAuthor(author)) {
            List<BookResponse> bookResponseList = new ArrayList<>();

            for (Book book : books) {
                bookResponseList.add(mapBookToResponse(book));
            }
            return bookResponseList;
        } else {
            throw new AuthorNotFoundException(ErrorCodeEnum.AUTHOR_NOT_FOUND_ERROR.getMessage());
        }
    }

    private BookResponse mapBookToResponse(Book book) {
        BookResponse bookResponse = new BookResponse();

        BeanUtils.copyProperties(book, bookResponse);
        return bookResponse;
    }


}
