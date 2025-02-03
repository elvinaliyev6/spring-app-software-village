package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(path = "/save-book")
    public BaseResponse saveBook(@RequestBody BookRequest bookRequest) {
        bookService.saveBook(bookRequest);
        return BaseResponse.getSuccessMessage();
    }

    @GetMapping(path = "/get-all")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/get-by-id/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping(path = "/get-books-by-author")
    public List<BookResponse> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }


}
