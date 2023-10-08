package dev.richryl.booksphere.rating;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public void createRating(@RequestBody @Valid Rating rating) {
        ratingService.save(rating);
    }

//    @PutMapping
//    public void updateRating(@RequestBody @Valid Rating rating) {
//        ratingService.save(rating);
//    }

    @PutMapping
    public void updateRating(@RequestParam String userId, @RequestParam Rating rating) {
        ratingService.updateRating(userId, rating);
    }

    @DeleteMapping(path = "delete")
    public void deleteRating(@RequestBody @Valid Rating rating) {
        ratingService.delete(rating);
    }

    @DeleteMapping(path = "deleteByUserIdAndBookId")
    public void deleteRatingByUserIdAndBookId(@RequestParam String userId, @RequestParam String bookId) {
        ratingService.deleteByUserIdAndBookId(userId, bookId);
    }

}
