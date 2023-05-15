package demo.service;

import demo.dto.CreateUserRequest;
import demo.dto.UpdateUserRequest;
import demo.dto.UserDto;
import demo.dto.UserDtoConverter;
import demo.exception.UserIsNotActiveException;
import demo.exception.UserNotFoundException;
import demo.model.Users;
import demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UsersRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getUser(Long id) {
        Users users = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return userDtoConverter.convert(users);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        Users users = new Users(null, userRequest.getFirstName(), userRequest.getMail(), userRequest.getMiddleName(), userRequest.getPostCode(), true);
        return userDtoConverter.convert(userRepository.save(users));
    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        Users users = findById(id);
        if (!users.getActive()) {
            throw new UserIsNotActiveException("user  not active");
        }
        Users updatedUser = new Users(users.getId(), users.getMail(), users.getPostCode(), updateUserRequest.getFirstName(), updateUserRequest.getMiddleName(), true);
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }


    public void deActiveUser(Long id) {
        changeActivateUser(id, false);
    }

    public void activeUser(Long id) {
        changeActivateUser(id, true);
    }

    public void changeActivateUser(Long id, Boolean isActive) {
        Users users = findById(id);

        if (!users.getActive()) {
            throw new UserIsNotActiveException("user  not active");
        }
        Users updatedUser = new Users(users.getId(),
                users.getMail(), users.getPostCode(), users.getFirstName(),
                users.getMiddleName(), isActive);
        userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        if(isExistUser(id)){
            userRepository.deleteById(id);
        }else{
            throw  new UserNotFoundException("user not found"+id);
        }
    }
    public boolean isExistUser(Long id) {
      return  userRepository.existsById(id);
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }


}
