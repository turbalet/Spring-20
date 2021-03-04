package kz.edu.rest;

import kz.edu.model.Answer;
import kz.edu.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@AllArgsConstructor
public class AnswerController {


    private final AnswerService answerService;

    @GetMapping
    public List<Answer> getAll(){
        return answerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Answer answer = answerService.getById(id);
        if(answer!=null){
            return ResponseEntity.ok(answer);
        }
        return ResponseEntity.status(404).body("Answer with such id doesn't exist");
    }

    @PostMapping("")
    public ResponseEntity<?> addAnswer(@RequestBody Answer answer){
        try{
            answerService.add(answer);
            return ResponseEntity.ok("Answer was successfully added");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAnswer(@RequestBody Answer answer){
        try{
            answerService.update(answer);
            return ResponseEntity.ok("Answer was successfully updated");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") long id){
        try{
            answerService.delete(id);
            return ResponseEntity.ok("Answer was successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @GetMapping("question/{id}")
    public List<Answer> getByQuestionId(@PathVariable("id") long id){
        return answerService.getAllByQuestionId(id);
    }

}
