package dev.richryl.booksphere.book;

import dev.richryl.booksphere.author.Author;
import dev.richryl.booksphere.author.AuthorRepository;
import dev.richryl.booksphere.book_author.BookAuthor;
import dev.richryl.booksphere.book_author.BookAuthorRepository;
import dev.richryl.booksphere.book_genre.BookGenre;
import dev.richryl.booksphere.book_genre.BookGenreRepository;
import dev.richryl.booksphere.exception.EntityException;
import dev.richryl.booksphere.genre.Genre;
import dev.richryl.booksphere.genre.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private static final int PAGE_SIZE = 15;
    private static final int SEARCH_BY_AUTHOR_PAGE_SIZE = 10;

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookGenreRepository bookGenreRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, BookGenreRepository bookGenreRepository, AuthorRepository authorRepository, BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    /**
     * return specified page of books
     *
     * @param page
     * @return page of books
     */
    public BookPageResult getBooks(Integer page, Integer size) {
        if(size == null) {
            size = PAGE_SIZE;
        }
        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(page, size, Sort.sort(Book.class).by(Book::getRating).descending()));
        List<Book> books = bookPage.toList();

        books.forEach(this::fillBookAdditionalInfo);

//        System.out.println("first one genres size = " + books.get(0).getGenres().size());

        return new BookPageResult(
            new PaginationInfo(
                    bookPage.getTotalElements(),
                    bookPage.getNumber(),
                    bookPage.getSize(),
                    bookPage.getTotalPages()
                    ),
                books
                );
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book findBookById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(
            () -> new EntityException("Book with id " + bookId + " does not exist")
        );
    }

    public BookPageResult findBookByTitle(String title, Integer page) {
        Page<Book> bookPage = bookRepository.findAllByTitleContainsIgnoreCaseOrderByRatingDesc(title, PageRequest.of(page, PAGE_SIZE));
        List<Book> books = bookPage.toList();

        books.forEach(this::fillBookAdditionalInfo);

        return new BookPageResult(new PaginationInfo(bookPage), books);
    }

    public Book findBookByIsbn13(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);

        fillBookAdditionalInfo(book);

        return book;
    }

    public BookPageResult findBookByAuthor(String author, Integer page) {
//        List<Integer> authors = authorRepository.findAllByNameContainsIgnoreCase(author, PageRequest.of(page,SEARCH_BY_AUTHOR_PAGE_SIZE)).stream().map(Author::getId).toList();
//
//        return bookRepository.findAllById(bookAuthorRepository.findBookAuthorsByAuthorIdIn(authors).stream().map(BookAuthor::getBookId).toList());
        Page<Author> authorPage = authorRepository.findAllByNameContainsIgnoreCase(author, PageRequest.of(page, SEARCH_BY_AUTHOR_PAGE_SIZE));
        List<Integer> authors = authorPage.toList().stream().map(Author::getId).toList();

        List<String> bookIds = bookAuthorRepository.findBookAuthorsByAuthorIdIn(authors).stream().map(BookAuthor::getBookId).toList();

        List<Book> books = bookRepository.findAllById(bookIds);

        books.forEach(this::fillBookAdditionalInfo);

        return new BookPageResult(new PaginationInfo(authorPage), books);
    }

    /**
     * Search books according to search term (title, author) and return specified page
     * @param searchTerm
     * @param page
     * @return page of books
     */
    public BookPageResult searchBooks(String searchTerm, Integer page) {
//        List<Book> books = new ArrayList<>();
//
//        books.addAll(findBookByTitle(searchTerm, page));
//        books.addAll(findBookByAuthor(searchTerm, page));

        return BookPageResult.merge(findBookByTitle(searchTerm, page), findBookByAuthor(searchTerm, page));
    }

    public BookPageResult findBookByGenre(Integer genreId, Integer page, Integer size) {
        if(size == null) {
            size = PAGE_SIZE;
        }
        Page<BookGenre> bookGenresPage = bookGenreRepository.findBookGenresByGenreId(genreId, PageRequest.of(page, size));
        List<String> bookIds = bookGenresPage.stream().map(BookGenre::getBookId).toList();

        List<Book> books = bookRepository.findAllById(bookIds);

        books.forEach(this::fillBookAdditionalInfo);
        return new BookPageResult(new PaginationInfo(bookGenresPage), books);
    }

    private void fillBookAdditionalInfo(Book book) {
        List<Integer> genresId = bookGenreRepository.findBookGenresByBookId(book.getBookId()).stream().map(BookGenre::getGenreId).toList();
//        System.out.println("book.getBookId() = " + book.getBookId() + " genresId = " + genresId.size());
        List<Integer> authorsId = bookAuthorRepository.findBookAuthorsByBookId(book.getBookId()).stream().map(BookAuthor::getAuthorId).toList();

//        System.out.println("book.getBookId() = " + book.getBookId() + " authorsId = " + authorsId.size());

        List<Genre> genres = genreRepository.findAllById(genresId);
        List<Author> authors = authorRepository.findAllById(authorsId);

        book.setGenres(genres.stream().map(Genre::getName).toList());
        book.setAuthors(authors.stream().map(Author::getName).toList());
    }

    public List<Book> findBooksByIds(List<String> ids) {
        List<Book> books = bookRepository.findAllById(ids);

        books.forEach(this::fillBookAdditionalInfo);

        return books;
    }
}
