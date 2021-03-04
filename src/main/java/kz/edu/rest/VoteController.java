package kz.edu.rest;

import kz.edu.model.Vote;
import kz.edu.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public List<Vote> getAll(){
        return voteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Vote vote = voteService.getById(id);
        if(vote!=null){
            return ResponseEntity.ok(vote);
        }
        return ResponseEntity.status(404).body("Vote with such id doesn't exist");
    }

    @PostMapping("")
    public ResponseEntity<?> addAnswer(@RequestBody Vote vote){
        try{
            voteService.add(vote);
            return ResponseEntity.ok("Vote was successfully added");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAnswer(@RequestBody Vote vote){
        try{
            voteService.update(vote);
            return ResponseEntity.ok("Vote was successfully updated");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") long id){
        try{
            voteService.delete(id);
            return ResponseEntity.ok("Vote was successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/question/{id}")
    List<Vote> getAllByQuestionId(@PathVariable("id") long id){
        return voteService.getAllByQuestionId(id);
    }

    @GetMapping("/answer/{id}")
    List<Vote> findAllByAnswerId(@PathVariable("id") long id){
        return voteService.getAllByAnswerId(id);
    }



    @GetMapping("/user/{id}")
    List<Vote> findAllByUserId(@PathVariable("id") long id){
        return voteService.getAllByUserId(id);
    }

}
