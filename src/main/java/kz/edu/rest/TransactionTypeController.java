package kz.edu.rest;



import kz.edu.model.TransactionType;
import kz.edu.service.TransactionTypeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tranType")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }


    @GetMapping("")
    public List<TransactionType> getAll(){
        return transactionTypeService.getAll();
    }

    @GetMapping("/{id}")
    public TransactionType getById(@PathVariable("id") int id){
        return transactionTypeService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavourite(@RequestBody TransactionType transactionType){
        try{
            transactionTypeService.add(transactionType);
        } catch (Exception e){

        }
        return ResponseEntity.ok(transactionType);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFavourite(@RequestBody TransactionType transactionType){
        try{
            transactionTypeService.update(transactionType);
        } catch (Exception e){

        }
        return ResponseEntity.ok(transactionType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable("id") int id){
        try {
            transactionTypeService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(200).body("Transaction type was removed");
    }
}
