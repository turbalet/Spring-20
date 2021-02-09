package kz.edu.rest;



import kz.edu.model.TransactionHistory;
import kz.edu.service.TransactionHistoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping("")
    public List<TransactionHistory> getAll(){
        return transactionHistoryService.getAll();
    }

    @GetMapping("/{id}")
    public TransactionHistory getById(@PathVariable("id") int id){
        return transactionHistoryService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavourite(@RequestBody TransactionHistory transactionHistory){
        try{
            transactionHistoryService.add(transactionHistory);
        } catch (Exception e){

        }
        return ResponseEntity.ok(transactionHistory);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFavourite(@RequestBody TransactionHistory transactionHistory){
        try{
            transactionHistoryService.update(transactionHistory);
        } catch (Exception e){

        }
        return ResponseEntity.ok(transactionHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable("id") int id){
        try {
            transactionHistoryService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(200).body("Transaction was removed");
    }
}
