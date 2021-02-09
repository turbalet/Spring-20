package kz.edu.rest;



import kz.edu.model.Favourite;
import kz.edu.service.FavouriteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;


    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping("")
    public List<Favourite> getAll(){
        return favouriteService.getFavourites();
    }

    @GetMapping("/{id}")
    public Favourite getById(@PathVariable("id") int id){
        return favouriteService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addFavourite(@RequestBody Favourite favourite){
        try{
            favouriteService.add(favourite);
        } catch (Exception e){

        }
        return ResponseEntity.ok(favourite);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFavourite(@RequestBody Favourite favourite){
        try{
            favouriteService.update(favourite);
        } catch (Exception e){

        }
        return ResponseEntity.ok(favourite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFavourite(@PathVariable("id") int id){
        try {
            favouriteService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

        return ResponseEntity.status(200).body("Favourite was removed");
    }
}
