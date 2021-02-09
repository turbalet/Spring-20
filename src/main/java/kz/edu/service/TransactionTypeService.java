package kz.edu.service;

import kz.edu.dao.TransactionTypeRepository;
import kz.edu.model.TransactionType;
import kz.edu.service.interfaces.ITransactionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService implements ITransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;


    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }


    public TransactionType getTransactionTypeByName(String name){
        return transactionTypeRepository.findByTypeName(name);
    }

    @Override
    public void add(TransactionType entity) {
        transactionTypeRepository.save(entity);
    }

    @Override
    public void update(TransactionType entity) {
        transactionTypeRepository.save(entity);
    }

    @Override
    public List<TransactionType> getAll() {
        return transactionTypeRepository.findAll();
    }

    @Override
    public TransactionType getById(int id) {
        return transactionTypeRepository.getOne(id);
    }

    @Override
    public void delete(int id) {
        transactionTypeRepository.deleteById(id);
    }
}
