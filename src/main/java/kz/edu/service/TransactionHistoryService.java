package kz.edu.service;

import kz.edu.dao.TransactionHistoryRepository;
import kz.edu.model.Account;
import kz.edu.model.TransactionHistory;
import kz.edu.service.interfaces.ITransactionHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService implements ITransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public List<TransactionHistory> getLatestIncomes(int id){
        return transactionHistoryRepository.getAllByReceiverId(id);
    }

    public List<TransactionHistory> getLatestOutcomes(int id){
        return transactionHistoryRepository.getAllBySenderId(id);
    }

    public List<TransactionHistory> getAll(int id){
        return transactionHistoryRepository.getAllBySenderIdOrReceiverId(id, id);
    }

    public double getSum(List<TransactionHistory> transactionHistories){
        return transactionHistories.isEmpty() ? 0 : transactionHistories.stream().mapToDouble(TransactionHistory::getAmount).sum();
    }

    public void save(TransactionHistory transactionHistory){
        transactionHistoryRepository.save(transactionHistory);
    }
}
