package com.project.neobis_back_neotour.mappers;


import com.project.neobis_back_neotour.entities.User;
import com.project.neobis_back_neotour.models.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .image_url(user.getImage_url())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();
    }

    public static User toEntity(UserDto user) {
        return User.builder()
                .username(user.getUsername())
                .image_url(user.getImage_url())
                .build();
    }
}