package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.request.BookRequest;
import az.softwarevillage.book.dto.response.BookResponse;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import az.softwarevillage.book.exception.BookAlreadyExist;
import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(BookRequest bookRequest){
        Book book = new Book();
        if(book.getTitle().equals(bookRequest.getTitle())){
            throw new BookAlreadyExist(ErrorCodeEnum.BOOK_ALREADY_EXIST_ERROR.getMessage());
        }

        BeanUtils.copyProperties(bookRequest, book);

        bookRepository.save(book);
    }

    public List<BookResponse> getAllBooks(){
       List<Book> books = bookRepository.findAll();

       List<BookResponse> bookResponses = new ArrayList<>();

       for(Book book : books){
           BookResponse bookResponse = new BookResponse();
           BeanUtils.copyProperties(book, bookResponse);
           bookResponses.add(bookResponse);
       }

       return bookResponses;
    }




}
