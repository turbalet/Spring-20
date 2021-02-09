package kz.edu.service;

import kz.edu.dao.FavouriteRepository;
import kz.edu.model.Favourite;
import kz.edu.model.User;
import kz.edu.service.interfaces.IFavouriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService implements IFavouriteService {

    private final FavouriteRepository favouriteRepository;


    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    public List<Favourite> getFavouritesByUserId(long id){
        return favouriteRepository.findAllByUserId(id);
    }

    public void addUserFav(User user, String accountNumber){
        favouriteRepository.save(new Favourite(accountNumber, user));
    }

    @Override
    public void add(Favourite entity) {

    }

    @Override
    public void update(Favourite entity) {

    }

    @Override
    public List<Favourite> getAll() {
        return favouriteRepository.findAll();
    }

    public Favourite getById(int id){
        return favouriteRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        favouriteRepository.deleteById(id);
    }

    public List<Favourite> getFavourites(){ return favouriteRepository.findAll();}
}
