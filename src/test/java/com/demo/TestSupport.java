package com.demo;

import demo.dto.UserDto;
import demo.model.UserInformation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static List<UserInformation> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i ->
                        new UserInformation((long) i, i + "mail",
                                i + "firstname", i + "middle", i + "postcode", true))
                .collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<UserInformation> userDtoList) {
        return userDtoList.stream().map(from ->
                        new UserDto(from.getMail(), from.getFirstName(), from.getMiddleName(), from.getPostCode()))
                .collect(Collectors.toList());
    }
}
