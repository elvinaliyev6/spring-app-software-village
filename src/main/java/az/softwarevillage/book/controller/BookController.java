package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }
    @PostMapping
    public BaseResponse addBook(@RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }
    @PutMapping("/{id}")
    public BaseResponse updateBook(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }
    @PutMapping("/delete/{id}")
    public BaseResponse deleteBook(@PathVariable("id") Long id) {
        return bookService.deleteBook(id);
    }
    @PutMapping("/borrow/{id}")
    public BaseResponse boorrowBook(@PathVariable("id") Long id) {
        return bookService.borrowBook(id);
    }
    @PutMapping("/return/{id}")
    public BaseResponse returnBook(@PathVariable("id") Long id) {
        return bookService.returnBook(id);
    }
}
