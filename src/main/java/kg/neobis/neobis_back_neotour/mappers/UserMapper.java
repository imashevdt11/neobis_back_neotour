package kg.neobis.neobis_back_neotour.mappers;

import kg.neobis.neobis_back_neotour.entities.User;
import kg.neobis.neobis_back_neotour.models.UserDto;

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
}