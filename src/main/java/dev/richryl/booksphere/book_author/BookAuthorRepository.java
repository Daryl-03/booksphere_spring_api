package dev.richryl.booksphere.book_author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {

    List<BookAuthor> findBookAuthorsByBookId(String bookId);

    List<BookAuthor> findBookAuthorsByAuthorId(Integer authorId);

    List<BookAuthor> findBookAuthorsByAuthorIdIn(List<Integer> authorIds);

    List<BookAuthor> findBookAuthorsByBookIdIn(List<String> bookIds);
}
