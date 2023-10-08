package dev.richryl.booksphere.book;

import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(@RequestParam @Min(0) Integer page, @RequestParam(required = false) @Min(0) Integer pageSize) {
        return bookService.getBooks(page, pageSize);
    }

    @GetMapping(path = "{bookId}")
    public Book findBookById(@PathVariable String bookId) {
        return bookService.findBookById(bookId);
    }

    @GetMapping(path = "search/{searchTerm}")
    public List<Book> searchBooks(@PathVariable String searchTerm, @RequestParam(required = false) @Min(0) Integer page) {
        return bookService.searchBooks(searchTerm, page==null?0:page);
    }

    @GetMapping(path = "searchByAuthor/{searchTerm}")
    public List<Book> searchBooksByAuthor(@PathVariable String searchTerm, @RequestParam(required = false) @Min(0) Integer page) {
        return bookService.findBookByAuthor(searchTerm, page==null?0:page);
    }

    @GetMapping(path = "getByGenre/{id}")
    public List<Book> getBooksByGenre(@PathVariable Integer id, @RequestParam(required = false) @Min(0) Integer page, @RequestParam(required = false) @Min(0) Integer pageSize) {
        return bookService.findBookByGenre(id, page==null?0:page, pageSize);
    }

    @GetMapping(path = "getByIsbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn13(isbn);
    }
}
