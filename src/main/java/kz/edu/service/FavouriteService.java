package kz.edu.service;

import kz.edu.dao.FavouriteRepository;
import kz.edu.model.Favourite;
import kz.edu.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;


    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public List<Favourite> getFavouritesByUserId(long id){
        return favouriteRepository.findAllByUserId(id);
    }

    public void add(User user, String accountNumber){
        favouriteRepository.save(new Favourite(accountNumber, user));
    }

    public Favourite getById(int id){
        return favouriteRepository.findById(id).orElse(null);
    }
}
