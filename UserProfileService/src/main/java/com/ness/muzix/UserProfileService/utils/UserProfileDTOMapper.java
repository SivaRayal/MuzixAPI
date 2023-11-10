package com.ness.muzix.UserProfileService.utils;

import com.ness.muzix.UserProfileService.entity.UserProfile;
import com.ness.muzix.UserProfileService.model.UserProfileResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProfileDTOMapper {
    public UserProfile getUpdatedEntityFromDTO(UserProfile oldEntity, UserProfileResponse newDTO){
        UserProfile newEntity = new UserProfile();
        newEntity.setUserEmail(Optional.ofNullable(newDTO.getUserEmail()).orElse(oldEntity.getUserEmail()));
        newEntity.setLastName(Optional.ofNullable(newDTO.getLastName()).orElse(oldEntity.getLastName()));
        newEntity.setFirstName(Optional.ofNullable(newDTO.getFirstName()).orElse(oldEntity.getFirstName()));
        newEntity.setContactNumber(Optional.ofNullable(newDTO.getContactNumber()).orElse(oldEntity.getContactNumber()));
        return newEntity;
    }
}
