package com.ness.muzix.WishListService.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ness.muzix.WishListService.model.WhislistDTO;

@Repository
public interface FavoritesRepository extends MongoRepository<WhislistDTO,String>{

}
