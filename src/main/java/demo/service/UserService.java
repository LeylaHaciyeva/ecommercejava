package demo.service;

import demo.dto.CreateUserRequest;
import demo.dto.UpdateUserRequest;
import demo.dto.UserDto;
import demo.dto.UserDtoConverter;
import demo.exception.UserIsNotActiveException;
import demo.exception.UserNotFoundException;
import demo.model.UserInformation;
import demo.repository.UserInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserInformationRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserInformationRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(x -> userDtoConverter.convert(x)).collect(Collectors.toList());
    }

    public UserDto getUser(Long id) {
        UserInformation userInformation = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return userDtoConverter.convert(userInformation);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        UserInformation userInformation = new UserInformation(null, userRequest.getFirstName(), userRequest.getMail(), userRequest.getMiddleName(), userRequest.getPostCode(), true);
        return userDtoConverter.convert(userRepository.save(userInformation));
    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        UserInformation userInformation = findById(id);
        if (!userInformation.getActive()) {
            throw new UserIsNotActiveException("user  not active");
        }
        UserInformation updatedUser = new UserInformation(userInformation.getId(), userInformation.getMail(), userInformation.getPostCode(), updateUserRequest.getFirstName(), updateUserRequest.getMiddleName(), true);
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }


    public void deActiveUser(Long id) {
        changeActivateUser(id, false);
    }

    public void activeUser(Long id) {
        changeActivateUser(id, true);
    }

    public void changeActivateUser(Long id, Boolean isActive) {
        UserInformation userInformation = findById(id);

        if (!userInformation.getActive()) {
            throw new UserIsNotActiveException("user  not active");
        }
        UserInformation updatedUser = new UserInformation(userInformation.getId(),
                userInformation.getMail(), userInformation.getPostCode(), userInformation.getFirstName(),
                userInformation.getMiddleName(), isActive);
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

    private UserInformation findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

}
