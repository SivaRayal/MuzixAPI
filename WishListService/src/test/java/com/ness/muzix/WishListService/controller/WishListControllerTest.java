package com.ness.muzix.WishListService.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;

import com.ness.muzix.WishListService.entities.Track;
import com.ness.muzix.WishListService.entities.WhislistDTO;
import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.model.WhislistRequest;
import com.ness.muzix.WishListService.service.WishListService;

@ExtendWith(MockitoExtension.class)
class WishListControllerTest {

	@Mock
	WishListService service;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	private WishListController controller;
	
	WhislistDTO whislistDTO = new WhislistDTO();
	Optional<WhislistDTO> wiOptional = Optional.of(new WhislistDTO());
	Optional<WhislistDTO> emOptional = Optional.empty();
	WhislistRequest requestWishlist = new WhislistRequest(); 
	BindingResult bindingResult = null; 

	@BeforeEach
	public void setup() {
		List<Track> listOfTrack = new ArrayList<>();
		
		Track track1 = new Track();
		track1.setArtist("Bonnie Lee");
		track1.setImage(null);
		track1.setTrackName("Perfect");
		track1.setUrl("https://www.youtube.com/watch?v=kPhpHvnnn0Q");
		
		Track track2 = new Track();
		track2.setArtist("Jonnie Lee");
		track2.setImage(null);
		track2.setTrackName("Impossible");
		track2.setUrl("https://www.youtube.com/watch?v=kPhpHvnnn0Q");

		listOfTrack.add(track1);
		listOfTrack.add(track2);
		whislistDTO.setTracks(listOfTrack);
		whislistDTO.setEmail("test@gmail.com");

		wiOptional.get().setTracks(listOfTrack);
		wiOptional.get().setEmail("test@gmail.com");
		
		requestWishlist.setEmail("test@gmail.com");
		com.ness.muzix.WishListService.model.Track  track3 = new com.ness.muzix.WishListService.model.Track();
		track3.setArtist("Tonnie Lee");
		track3.setImage(null);
		track3.setTrackName("Invisible");
		track3.setUrl("https://www.youtube.com/watch?v=kPhpHvnnn0Q");
		List<com.ness.muzix.WishListService.model.Track> list = new ArrayList<>();
		list.add(track3);
		requestWishlist.setTracks(list);
		
		
	}

	@Test
	void testGetFavorites() {
		when(service.getFavourites("test@gmail.com")).thenReturn(whislistDTO);
		controller.getFavorites("test@gmail.com");
		
	}

	@Test
	void testUpdateFavorites() {
		//when(bindingResult.hasErrors()).thenReturn(false);
		//when(modelMapper.map(requestWishlist, WhislistDTO.class)).thenReturn(whislistDTO);
		//when(bindingResult.hasErrors()).thenReturn(true);
		//when(service.addToFavorites(whislistDTO)).thenReturn(wiOptional);
		//WishListException exc = Assertions.assertThrows(WishListException.class, ()->service.addToFavorites(whislistDTO));
		
		//controller.updateFavorites(requestWishlist,bindingResult);
		
	}

	@Test
	void testRemoveFavorites() {
		
	}

}
