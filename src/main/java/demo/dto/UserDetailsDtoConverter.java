package demo.dto;

import demo.model.UserDetails;
import demo.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {
    public UserDetailsDto convert(UserDetails from) {
        return new UserDetailsDto(
                from.getCity(), from.getAddress(), from.getCountry(),
                from.getPhoneNumber(),from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> fromList){
        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }
}
