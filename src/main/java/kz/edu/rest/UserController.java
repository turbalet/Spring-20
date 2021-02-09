package kz.edu.rest;




import kz.edu.model.User;
import kz.edu.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavourite(@RequestBody User user){
        try{
            userService.add(user);
        } catch (Exception e){

        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFavourite(@RequestBody User user){
        try{
            userService.update(user);
        } catch (Exception e){

        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable("id") long id){
        try {
            userService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(200).body("User was removed");
    }
}
