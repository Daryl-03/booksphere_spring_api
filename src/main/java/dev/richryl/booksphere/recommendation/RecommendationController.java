package dev.richryl.booksphere.recommendation;

import dev.richryl.booksphere.book.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "recommendations", description = "The Recommendation endpoint")
@RestController
@RequestMapping(path = "api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Operation(
            summary = "Get recommendations",
            description = "Get recommendations for a user. Requires authentication",
            tags = {"Recommendation", "Get"}
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
        }
    )
    @GetMapping
    public List<Book> getRecommendations(@RequestParam String userId) {
        return recommendationService.getRecommendations(userId);
    }
}
