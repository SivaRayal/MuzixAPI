package com.ness.muzix.WishListService.service;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.model.WhislistDTO;

public interface WishListService {
    WhislistDTO getFavourites(String email) throws WishListException;
    Optional<WhislistDTO> addToFavorites(WhislistDTO update) throws WishListException;
    void removeFromFavorites(WhislistDTO remove) throws WishListException;
}
