package dev.richryl.booksphere.recommendation;

import dev.richryl.booksphere.book.Book;
import dev.richryl.booksphere.book.BookRepository;
import dev.richryl.booksphere.book.BookService;
import dev.richryl.booksphere.rating.RatingRepository;
import dev.richryl.booksphere.user_history.BookState;
import dev.richryl.booksphere.user_history.UserHistory;
import dev.richryl.booksphere.user_history.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;
    private final UserHistoryRepository userHistoryRepository;
    private final RatingRepository ratingRepository;
    private final BookService bookService;
    private static final String RECOMMENDATION_API_URL = System.getenv("RECOMMENDATION_API_URL");

    @Autowired
    public RecommendationService(UserHistoryRepository userHistoryRepository, RatingRepository ratingRepository, BookService bookService) {
        this.ratingRepository = ratingRepository;
        this.bookService = bookService;
        this.restTemplate = new RestTemplate();
        this.userHistoryRepository = userHistoryRepository;
    }

    public List<Book> getRecommendations(String userId) {
        List<UserHistory> history = userHistoryRepository.findAllByUserId(userId);

        FlaskBookInput flaskBookInput = getFlaskBookInput(history);

        List<String> recommendedBookIds = getRecommendedBookIds(flaskBookInput);

        if (recommendedBookIds != null) {
            List<Book> recommendedBooks = bookService.findBooksByIds(recommendedBookIds);
            return recommendedBooks;
        }

        return new ArrayList<Book>();
    }

    private List<String> getRecommendedBookIds(FlaskBookInput flaskBookInput) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        HttpEntity<FlaskBookInput> request = new HttpEntity<>(flaskBookInput, headers);

        List<String> recommendedBookIds = null;
        try {
            ResponseEntity<String[]> response = restTemplate.postForEntity(RECOMMENDATION_API_URL, request, String[].class);

            if (response.getBody() != null)
                recommendedBookIds = Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return recommendedBookIds;
    }

    private FlaskBookInput getFlaskBookInput(List<UserHistory> history) {
        Map<String, Double> bookIdMap = new HashMap<>();
        List<String> unreadBookIds = new ArrayList<>();

        for (UserHistory userHistory : history) {
            if(userHistory.getBookState() == BookState.READ)
                bookIdMap.put(userHistory.getBookId(), ratingRepository.averageByBookId(userHistory.getBookId()));
            else
                unreadBookIds.add(userHistory.getBookId());
        }

        // time to request the recommendations from the Flask API
        FlaskBookInput flaskBookInput = new FlaskBookInput();
        flaskBookInput.setBookIdMap(bookIdMap);
        flaskBookInput.setUnreadBookIds(unreadBookIds);
        return flaskBookInput;
    }
}
