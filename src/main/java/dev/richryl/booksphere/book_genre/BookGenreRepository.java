package dev.richryl.booksphere.book_genre;

import dev.richryl.booksphere.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookGenreRepository extends JpaRepository<BookGenre, Integer> {

    List<BookGenre> findBookGenresByBookId(String bookId);

    Page<BookGenre> findBookGenresByGenreId(Integer genreId, Pageable pageable);

    List<BookGenre> findBookGenresByBookIdIn(List<String> bookIds);

    Page<BookGenre> findBookGenresByGenreIdIn(List<Integer> genreIds, Pageable pageable);
}
