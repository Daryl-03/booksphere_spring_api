package dev.richryl.booksphere.user_history;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user_history")
@Validated
public class UserHistoryController {

    private final UserHistoryService userHistoryService;

    public UserHistoryController(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @PostMapping
    public void create(@RequestBody @Valid UserHistory userHistory) {
        userHistoryService.save(userHistory);
    }

    @DeleteMapping(path = "/delete")
    public void delete(@RequestBody @Valid UserHistory userHistory) {
        userHistoryService.deleteByUserIdAndBookId(userHistory.getUserId(), userHistory.getBookId());
    }

    @GetMapping(path = "/getByUserIdAndBookId")
    public UserHistory getUserHistoryByUserIdAndBookId(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
        return userHistoryService.findByUserIdAndBookId(userId, bookId);
    }

    @GetMapping(path = "/getByUserId")
    public List<UserHistory> getByUserId(@RequestParam("userId") String userId) {
        return userHistoryService.findAllByUserId(userId);
    }

    @GetMapping(path = "/{id}")
    public UserHistory getUserHistoryById(@PathVariable("id") Integer id) {
        return userHistoryService.findById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        userHistoryService.deleteById(id);
    }

    @DeleteMapping(path = "/deleteByUserIdAndBookId")
    public void deleteByUserIdAndBookId(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
        userHistoryService.deleteByUserIdAndBookId(userId, bookId);
    }
}
