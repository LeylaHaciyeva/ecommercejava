package demo.service;

import demo.dto.CreateUserRequest;
import demo.dto.UpdateUserRequest;
import demo.dto.UserDto;
import demo.dto.UserDtoConverter;
import demo.exception.UserNotFoundException;
import demo.model.User;
import demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(x -> userDtoConverter.convert(x)).collect(Collectors.toList());
    }
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return userDtoConverter.convert(user);
    }
    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(null, userRequest.getFirstName(), userRequest.getMail(), userRequest.getMiddleName(), userRequest.getPostCode());
        return userDtoConverter.convert(userRepository.save(user));
    }
    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findById(id);
        User updatedUser = new User(user.getId(), user.getMail(), user.getPostCode(), updateUserRequest.getFirstName(), updateUserRequest.getMiddleName());
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

//    public void deActivedUser(Long id) {
//    }
//
//    public void deleteUser(Long id) {
//    }
}
