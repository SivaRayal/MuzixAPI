package com.ness.muzix.WishListService.service;

import java.util.Optional;

import com.ness.muzix.WishListService.entities.WhislistDTO;
import com.ness.muzix.WishListService.exception.WishListException;

public interface WishListService {
    WhislistDTO getFavourites(String email) throws WishListException;
    Optional<WhislistDTO> addToFavorites(WhislistDTO update) throws WishListException;
    void removeFromFavorites(WhislistDTO remove) throws WishListException;
}
