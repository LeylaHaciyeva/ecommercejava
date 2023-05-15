package demo.service;

import demo.dto.CreateUserDetailsRequest;
import demo.dto.UpdateUserDetailsRequest;
import demo.dto.UserDetailsDto;
import demo.dto.UserDetailsDtoConverter;
import demo.exception.UserDetailsNotException;
import demo.model.UserDetails;
import demo.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsDtoConverter converter;

    public UserDetailsService(UserDetailsRepository userDetailsRepository,
                              UserService userService,
                              UserDetailsDtoConverter converter) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.converter = converter;
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request) {
//        Users user =userService.findById(request.getUserId());
        UserDetails userDetails = new UserDetails(
                request.getCity(), request.getAddress(), request.getCountry(), request.getPostCode(), request.getPhoneNumber(),
                userService.findById(request.getUserId())
                );
        return converter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final Long userDetailsId, final UpdateUserDetailsRequest request) {
        UserDetails userDetails = findUserDetailsById(userDetailsId);
        UserDetails updateUserDetails = new UserDetails(
                userDetails.getId(),
                request.getAddress(),
                request.getCity(), request.getCountry(), request.getPhoneNumber(),
                request.getPostCode(), userDetails.getUsers());
        return converter.convert(userDetailsRepository.save(updateUserDetails));
    }

    private UserDetails findUserDetailsById(final Long userDetailsId) {
        return userDetailsRepository.findById(userDetailsId).orElseThrow(() -> new UserDetailsNotException("user details not founddd"));
    }

    public void deleteUserDetails(final Long id) {
        userDetailsRepository.deleteById(id);
    }
}
