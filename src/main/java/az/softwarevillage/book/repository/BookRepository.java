package az.softwarevillage.book.repository;

import az.softwarevillage.book.model.Book;
import az.softwarevillage.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByStatus(Integer status);
    Book findByIdAndStatus(Long id, Integer status);
}
