package com.ness.muzix.WishListService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.model.WhislistDTO;
import com.ness.muzix.WishListService.service.WishListService;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	@Autowired
	WishListService wishlistService;
	
	@GetMapping("/getFavorites")
	public ResponseEntity<?> getFavorites(@RequestParam String userEmail){
		return new ResponseEntity<>(wishlistService.getFavourites(userEmail),HttpStatus.OK);
	}
	
	@PostMapping("/updateFavorites")
	public ResponseEntity<WhislistDTO> updateFavorites(@Validated @RequestBody WhislistDTO requestWishlist, BindingResult result){
		//Bindning Error handle
         if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError res : result.getFieldErrors()) {
                errors.add(res.getDefaultMessage());
            }
            throw new WishListException(errors.toString());
        }
		return new ResponseEntity<>(wishlistService.addToFavorites(requestWishlist).get(),HttpStatus.OK);
	}

	@DeleteMapping("/removeFavorites")
	public ResponseEntity<?> removeFavorites(@Validated @RequestBody WhislistDTO requestWishlist, BindingResult result){
		//Bindning Error handle
         if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError res : result.getFieldErrors()) {
                errors.add(res.getDefaultMessage());
            }
            throw new WishListException(errors.toString());
        }
		wishlistService.removeFromFavorites(requestWishlist);
		return new ResponseEntity<>("Tracks removed from favourites list",HttpStatus.OK);
	}

}
