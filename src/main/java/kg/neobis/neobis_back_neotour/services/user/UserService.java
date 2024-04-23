package kg.neobis.neobis_back_neotour.services.user;


import kg.neobis.neobis_back_neotour.models.UserDto;

import java.util.List;


public interface UserService {

    UserDto addUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}