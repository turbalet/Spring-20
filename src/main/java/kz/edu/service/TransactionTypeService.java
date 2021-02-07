package kz.edu.service;

import kz.edu.dao.TransactionTypeRepository;
import kz.edu.model.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;


    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }


    public TransactionType getTransactionTypeByName(String name){
        return transactionTypeRepository.findByTypeName(name);
    }
}
