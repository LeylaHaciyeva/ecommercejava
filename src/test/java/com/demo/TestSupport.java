package com.demo;

import demo.dto.UserDto;
import demo.model.Users;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static List<Users> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i ->
                        new Users((long) i, i + "mail",
                                i + "firstname", i + "middle", i + "postcode", true))
                .collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<Users> userDtoList) {
        return userDtoList.stream().map(from ->
                        new UserDto(from.getMail(), from.getFirstName(), from.getMiddleName(), from.getPostCode()))
                .collect(Collectors.toList());
    }
}
