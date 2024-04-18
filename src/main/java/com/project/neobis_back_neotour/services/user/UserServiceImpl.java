package com.project.neobis_back_neotour.services.user;

import com.project.neobis_back_neotour.entities.User;
import com.project.neobis_back_neotour.exceptions.UserNotFoundException;
import com.project.neobis_back_neotour.models.UserDto;
import com.project.neobis_back_neotour.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .image_url(userDto.getImage_url())
                .build();

        User savedUser = userRepository.save(user);
        return convertToUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(this::convertToUserDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        UserDto user = getUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        user.setUsername(userDto.getUsername());
        user.setImage_url(userDto.getImage_url());

        User updatedUser = userRepository.save(convertToUserEntity(user));
        return convertToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalProduct = userRepository.findById(id);

        if (optionalProduct.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    public UserDto convertToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .image_url(user.getImage_url())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();
    }

    public User convertToUserEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .image_url(user.getImage_url())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();
    }
}