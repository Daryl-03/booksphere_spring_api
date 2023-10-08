package dev.richryl.booksphere.user_history;

import org.springframework.stereotype.Service;

@Service
public class UserHistoryService {

    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryService(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    public UserHistory save(UserHistory userHistory) {
        int id = userHistoryRepository.findByUserIdAndBookId(userHistory.getUserId(), userHistory.getBookId());
        if (id != 0) {
            userHistory.setId(id);
        }
        return userHistoryRepository.save(userHistory);
    }

    public Integer findByUserIdAndBookId(String userId, String bookId) {
        return userHistoryRepository.findByUserIdAndBookId(userId, bookId);
    }

    public UserHistory findById(Integer id) {
        return userHistoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        userHistoryRepository.deleteById(id);
    }

    public void deleteByUserIdAndBookId(String userId, String bookId) {
        userHistoryRepository.deleteById(findByUserIdAndBookId(userId, bookId));
    }
}
