package com.ness.muzix.WishListService.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ness.muzix.WishListService.entities.WhislistDTO;

@Component
@Repository
public interface FavoritesRepository extends MongoRepository<WhislistDTO,String>{

}
