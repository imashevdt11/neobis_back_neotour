package kg.neobis.neobis_back_neotour.services.user;

import kg.neobis.neobis_back_neotour.entities.User;
import kg.neobis.neobis_back_neotour.exceptions.UserNotFoundException;
import kg.neobis.neobis_back_neotour.mappers.UserMapper;
import kg.neobis.neobis_back_neotour.models.UserDto;
import kg.neobis.neobis_back_neotour.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .image_url(userDto.getImage_url())
                .build();
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return userList.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setImage_url(userDto.getImage_url());
            user.setUpdated_at(LocalDateTime.now());

            User updatedUser = userRepository.save(user);
            return UserMapper.toDto(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
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
}