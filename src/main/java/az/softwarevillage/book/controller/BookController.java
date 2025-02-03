package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "books")
public class BookController {
    private final BookService bookService;

    @PostMapping("/register")
    public BaseResponse registerBook(@RequestBody BookRequest bookRequest) {
        return bookService.registerBook(bookRequest);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BaseResponse updateBook(@PathVariable("id") Long id, @RequestBody BookRequest bookRequest) {
        return bookService.updateUser(id, bookRequest);
    }

}
