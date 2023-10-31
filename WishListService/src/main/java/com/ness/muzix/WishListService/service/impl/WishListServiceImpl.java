package com.ness.muzix.WishListService.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.model.WhislistDTO;
import com.ness.muzix.WishListService.repo.FavoritesRepository;
import com.ness.muzix.WishListService.service.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WishListServiceImpl implements WishListService{
    @Autowired
    FavoritesRepository favoritesRepo;

    @Override
    public WhislistDTO getFavourites(String email) throws WishListException{
        return favoritesRepo.findById(email).orElseThrow(()->new WishListException("No Favourites in the list"));
    }

    @Override
    public Optional<WhislistDTO> addToFavorites(WhislistDTO update) throws WishListException {
        Optional<WhislistDTO> optionalResp =  favoritesRepo.findById(update.getEmail());
        if(optionalResp.isEmpty()){
            favoritesRepo.save(update);
            log.debug("Added to Favorites New");
        }else{
            boolean oldResponse = optionalResp.get().getTracks().addAll(update.getTracks());
            log.debug("addToFavorites Update - "+oldResponse);
        }
        return Optional.of(update);
    }

    @Override
    public void removeFromFavorites(WhislistDTO remove) throws WishListException {
        Optional<WhislistDTO> optionalResp =  favoritesRepo.findById(remove.getEmail());
        if(optionalResp.isEmpty()){
            log.debug("Remove From Favorites");
            throw new WishListException("No Favourites in the list");
        }else{
            WhislistDTO removed = optionalResp.get();
            boolean oldResponse = removed.getTracks().removeAll(remove.getTracks());
            favoritesRepo.save(removed);
            log.debug("removed Favorites - "+oldResponse);
        }
    }
    
}
