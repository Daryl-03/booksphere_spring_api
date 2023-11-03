package dev.richryl.booksphere.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findAllByTitleContainsIgnoreCaseOrderByRatingDesc(String title, Pageable pageable);

    Book findByIsbn(String isbn13);

}
