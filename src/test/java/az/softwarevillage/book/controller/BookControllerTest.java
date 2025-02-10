package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookResponse bookResponse;
    private List<BookResponse> bookResponseList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        BookResponse bookResponse1 = new BookResponse();
        bookResponse1.setAuthor("author1");
        bookResponse1.setTitle("title1");

        this.bookResponse = bookResponse1;

        BookResponse bookResponse2 = new BookResponse();
        bookResponse2.setAuthor("author2");
        bookResponse2.setTitle("title2");

        bookResponseList.add(bookResponse1);
        bookResponseList.add(bookResponse2);
    }

    @Test
    void testAddBook() throws Exception {

        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("title1");
        bookRequest.setAuthor("author1");

        when(bookService.addBook(any(BookRequest.class)))
                .thenReturn(BaseResponse.getSuccessMessage());

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void getBooksTest() throws Exception {
        when(bookService.getAllBooks())
                .thenReturn(bookResponseList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void getBookSuccess() throws Exception {
        when(bookService.getBookById(any()))
                .thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 4L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("author1"))
                .andExpect(jsonPath("$.title").value("title1"));
    }

    private String toJson(Object object) throws JsonProcessingException {
       return objectMapper.writeValueAsString(object);
    }

}