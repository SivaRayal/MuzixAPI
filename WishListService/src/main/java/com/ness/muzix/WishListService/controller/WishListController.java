package com.ness.muzix.WishListService.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ness.muzix.WishListService.entities.WhislistDTO;
import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.model.WhislistRequest;
import com.ness.muzix.WishListService.service.WishListService;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	@Autowired
	WishListService wishlistService;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/getFavorites")
	public ResponseEntity<?> getFavorites(@RequestHeader("email") String email){
		return new ResponseEntity<>(wishlistService.getFavourites(email),HttpStatus.OK);
	}
	
	@PostMapping("/updateFavorites")
	public ResponseEntity<WhislistRequest> updateFavorites(@Validated @RequestBody WhislistRequest requestWishlist, BindingResult result){
		//Bindning Error handle
         if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError res : result.getFieldErrors()) {
                errors.add(res.getDefaultMessage());
            }
            throw new WishListException("updateFavorites failed due to invalid request");
        }
		WhislistDTO wishListDTO=modelMapper.map(requestWishlist, WhislistDTO.class);
		WhislistRequest wishListResp= modelMapper.map(wishlistService.addToFavorites(wishListDTO).get(),WhislistRequest.class);
		return new ResponseEntity<>(wishListResp,HttpStatus.OK);
	}

	@DeleteMapping("/removeFavorites")
	public ResponseEntity<?> removeFavorites(@Validated @RequestBody WhislistRequest requestWishlist, BindingResult result){
		//Bindning Error handle
         if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError res : result.getFieldErrors()) {
                errors.add(res.getDefaultMessage());
            }
            throw new WishListException("removeFavorites failed due to bad request");
        }
		wishlistService.removeFromFavorites(modelMapper.map(requestWishlist, WhislistDTO.class) );
		return new ResponseEntity<>("Tracks removed from favourites list",HttpStatus.OK);
	}

}
