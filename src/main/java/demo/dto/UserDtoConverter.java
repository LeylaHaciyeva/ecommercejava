package demo.dto;

import demo.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    private final UserDetailsDtoConverter converter;

    public UserDtoConverter( UserDetailsDtoConverter converter) {
        this.converter = converter;
    }

    public UserDto convert(Users from){
        return new UserDto(from.getMail(),from.getFirstName(),
                from.getMiddleName(),from.getPostCode(),converter.convert(new ArrayList<>(from.getUserDetails())))
                ;
    }
    public List<UserDto> convert(List<Users> fromList){
        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }
}