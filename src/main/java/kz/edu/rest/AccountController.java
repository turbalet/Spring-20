package kz.edu.rest;



import kz.edu.model.Account;

import kz.edu.service.AccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") int id){
        return accountService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavourite(@RequestBody Account account){
        try{
            accountService.add(account);
        } catch (Exception e){

        }
        return ResponseEntity.ok(account);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFavourite(@RequestBody Account account){
        try{
            accountService.update(account);
        } catch (Exception e){

        }
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable("id") int id){
        try {
            accountService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(200).body("Account was removed");
    }

}
