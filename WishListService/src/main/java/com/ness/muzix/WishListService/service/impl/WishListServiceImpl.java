package com.ness.muzix.WishListService.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.muzix.WishListService.entities.Track;
import com.ness.muzix.WishListService.entities.WhislistDTO;
import com.ness.muzix.WishListService.exception.WishListException;
import com.ness.muzix.WishListService.repo.FavoritesRepository;
import com.ness.muzix.WishListService.service.WishListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("WishListService")
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
            log.info("Added to Favorites New");
        }else{
            WhislistDTO wishList=optionalResp.get();
            List<Track> updateTracks = wishList.getTracks();
            for(Track newTracks:update.getTracks()){
                updateTracks.removeIf(track->(track.getTrackName()+track.getArtist()).equalsIgnoreCase(newTracks.getTrackName()+newTracks.getArtist()));
            }
            boolean oldResponse = updateTracks.addAll(update.getTracks());
            wishList.setTracks(updateTracks);
            favoritesRepo.save(wishList);
            log.info("addToFavorites Update - "+oldResponse);
        }
        return Optional.of(update);
    }

    @Override
    public void removeFromFavorites(WhislistDTO remove) throws WishListException {
        Optional<WhislistDTO> optionalResp =  favoritesRepo.findById(remove.getEmail());
        if(optionalResp.isEmpty()){
            log.info("Nothing to remove from Favorites");
            throw new WishListException("No Favourites in the list");
        }else{
            WhislistDTO finalResp = optionalResp.get();
            log.info("Before removing Favorites - "+finalResp.toString());

            // HashSet<String> finalList = new HashSet<>();
            // finalResp.getTracks().removeIf(track->!finalList.add(track.getTrackName()+track.getArtist()));

            for(Track track:remove.getTracks()){
                finalResp.getTracks().removeIf(currentTrack->(currentTrack.getTrackName()+currentTrack.getArtist()).equalsIgnoreCase(track.getTrackName()+track.getArtist()));
            }
            favoritesRepo.deleteById(finalResp.getEmail());
            log.info("After removing Favorites - "+finalResp.toString());
            // boolean oldResponse = removed.getTracks().removeAll(remove.getTracks());
            favoritesRepo.save(finalResp);
            log.info("removed Favorites - "+finalResp.toString());
        }
    }
    
}
