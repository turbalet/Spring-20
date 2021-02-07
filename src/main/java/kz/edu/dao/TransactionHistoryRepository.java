package kz.edu.dao;

import kz.edu.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    List<TransactionHistory> getAllBySenderId(int id);
    List<TransactionHistory> getAllByReceiverId(int id);
    List<TransactionHistory> getAllBySenderIdOrReceiverId(int sender, int receiver);
}
