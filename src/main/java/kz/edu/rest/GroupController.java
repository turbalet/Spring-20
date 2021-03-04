package kz.edu.rest;

import kz.edu.model.Group;
import kz.edu.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<Group> getAll(){
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        Group group = groupService.getById(id);
        if(group!=null){
            return ResponseEntity.ok(group);
        }
        return ResponseEntity.status(404).body("Group with such id doesn't exist");
    }

    @PostMapping("")
    public ResponseEntity<?> addGroup(@RequestBody Group group){
        try{
            groupService.add(group);
            return ResponseEntity.ok("Group was successfully added");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGroup(@RequestBody Group group){
        try{
            groupService.update(group);
            return ResponseEntity.ok("Group was successfully updated");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") long id){
        try{
            groupService.delete(id);
            return ResponseEntity.ok("Group was successfully deleted");
        } catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



}
