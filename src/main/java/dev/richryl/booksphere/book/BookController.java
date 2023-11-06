package dev.richryl.booksphere.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "The Book API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/testFetch")
    public BookPageResult testFetch(@RequestParam(required = false) @Min(0) Integer page, @RequestParam(required = false) @Min(0) Integer pageSize) {
        return bookService.testFetch(page, pageSize);
    }

    @Operation(
            summary = "Get all books",
            description = "Get all books",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping
    public BookPageResult getBooks(@RequestParam @Min(0) Integer page, @RequestParam(required = false) @Min(0) Integer pageSize) {
        return bookService.getBooks(page, pageSize);
    }

    @Operation(
            summary = "Get book by id",
            description = "Get book by id",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "{bookId}")
    public Book findBookById(@PathVariable String bookId) {
        return bookService.findBookById(bookId);
    }

    @Operation(
            summary = "search books",
            description = "search books by title",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "search/{searchTerm}")
    public BookPageResult searchBooks(@PathVariable String searchTerm, @RequestParam(required = false) @Min(0) Integer page) {
        return bookService.searchBooks(searchTerm, page == null ? 0 : page);
    }

    @Operation(
            summary = "search books by author",
            description = "search books by author name",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "searchByAuthor/{searchTerm}")
    public BookPageResult searchBooksByAuthor(@PathVariable String searchTerm, @RequestParam(required = false) @Min(0) Integer page) {
        return bookService.findBookByAuthor(searchTerm, page == null ? 0 : page);
    }

    @Operation(
            summary = "search books by title",
            description = "search books by title",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "searchByTitle/{searchTerm}")
    public BookPageResult searchBooksByTitle(@PathVariable String searchTerm, @RequestParam(required = false) @Min(0) Integer page) {
        return bookService.findBookByTitle(searchTerm, page == null ? 0 : page);
    }

    @Operation(
            summary = "search books by genre",
            description = "search books by genre",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "getByGenre/{id}")
    public BookPageResult getBooksByGenre(@PathVariable Integer id, @RequestParam(required = false) @Min(0) Integer page, @RequestParam(required = false) @Min(0) Integer pageSize) {
        return bookService.findBookByGenre(id, page == null ? 0 : page, pageSize);
    }

    @Operation(
            summary = "search books by isbn",
            description = "search books by its isbn number",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(path = "getByIsbn/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn13(isbn);
    }

    @Operation(
            summary = "search books by a list of ids",
            description = "search books by a list of ids",
            tags = {"Book", "Get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping("/getBooksByIds")
    public List<Book> getBooksByIds(@RequestParam List<String> ids) {
        return bookService.findBooksByIds(ids);
    }
}
