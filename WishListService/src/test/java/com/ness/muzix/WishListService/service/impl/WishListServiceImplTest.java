package com.ness.muzix.WishListService.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.ness.muzix.WishListService.entities.Track;
import com.ness.muzix.WishListService.entities.WhislistDTO;
import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.repo.FavoritesRepository;

@ExtendWith(MockitoExtension.class)
class WishListServiceImplTest {

	@Mock
	FavoritesRepository favoritesRepo;

	@InjectMocks
	private WishListServiceImpl service;

	WhislistDTO whislistDTO = new WhislistDTO();
	Optional<WhislistDTO> wiOptional = Optional.of(new WhislistDTO());
	Optional<WhislistDTO> emOptional = Optional.empty();

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
	}

	@Test
	void testGetFavourites() {
		when(favoritesRepo.findById("test@gmail.com")).thenReturn(wiOptional);
		service.getFavourites("test@gmail.com");

		assertNotNull(wiOptional);
		assertEquals(wiOptional.get().getTracks().get(0).getArtist(), "Bonnie Lee");
	}

	@Test
	void testAddToFavoritesNotEmpty() {
		when(favoritesRepo.findById("test@gmail.com")).thenReturn(wiOptional);
		when(favoritesRepo.save(whislistDTO)).thenReturn(null);

		service.addToFavorites(whislistDTO);
		assertNotNull(emOptional);
		assertTrue(emOptional.isEmpty());
	}

	@Test
	void testAddToFavorites() {
		when(favoritesRepo.findById("test@gmail.com")).thenReturn(emOptional);
		when(favoritesRepo.save(whislistDTO)).thenReturn(null);

		service.addToFavorites(whislistDTO);
		assertNotNull(emOptional);
		assertTrue(emOptional.isEmpty());
	}

	@Test
	void testRemoveFromFavorites() {
		when(favoritesRepo.findById("test@gmail.com")).thenReturn(emOptional);
		WishListException exc = Assertions.assertThrows(WishListException.class, ()->service.removeFromFavorites(whislistDTO));
		
		assertTrue(emOptional.isEmpty());
	}
	
	@Test
	void testRemoveFromFavoritesWhislistDTO() {
		when(favoritesRepo.findById("test@gmail.com")).thenReturn(wiOptional);
		when(favoritesRepo.save(whislistDTO)).thenReturn(null);
		
		
		service.removeFromFavorites(whislistDTO);
		assertTrue(emOptional.isEmpty());
	}

}
