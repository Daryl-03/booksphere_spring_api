package dev.richryl.booksphere.user_history;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService {

    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryService(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    public UserHistory save(UserHistory userHistory) {
        int id = userHistoryRepository.findByUserIdAndBookId(userHistory.getUserId(), userHistory.getBookId()).getId();
        if (id != 0) {
            userHistory.setId(id);
        }
        return userHistoryRepository.save(userHistory);
    }

    public UserHistory findByUserIdAndBookId(String userId, String bookId) {
        return userHistoryRepository.findByUserIdAndBookId(userId, bookId);
    }

    public UserHistory findById(Integer id) {
        return userHistoryRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        userHistoryRepository.deleteById(id);
    }

    public void deleteByUserIdAndBookId(String userId, String bookId) {
        userHistoryRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    public void deleteAllByUserId(String userId) {
        userHistoryRepository.deleteAll(userHistoryRepository.findAllByUserId(userId));
    }

    public void deleteAll() {
        userHistoryRepository.deleteAll();
    }

    public Integer countByUserId(String userId) {
        return userHistoryRepository.findAllByUserId(userId).size();
    }

    public boolean existsByUserIdAndBookId(String userId, String bookId) {
        return userHistoryRepository.findByUserIdAndBookId(userId, bookId) != null;
    }

    public List<UserHistory> findAllByUserId(String userId) {
        return userHistoryRepository.findAllByUserId(userId);
    }
}
