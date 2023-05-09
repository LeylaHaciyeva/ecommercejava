package com.demo;

import demo.dto.UserDto;
import demo.dto.UserDtoConverter;
import demo.model.UserInformation;
import demo.repository.UserInformationRepository;
import demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UserServiceTest extends TestSupport {
    private UserInformationRepository repository;
    private UserService service;
    private UserDtoConverter converter;

    @BeforeEach
    public void setup() {
        converter = mock(UserDtoConverter.class);
        repository = mock(UserInformationRepository.class);
        service = new UserService(repository, converter);
    }

    @Test
    public void getAllUsersTest() {
        List<UserInformation> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        Mockito.when(repository.findAll()).thenReturn(userList);
        Mockito.when(repository.findAll()).thenReturn(userList);
        Mockito.when(converter.convert(userList)).thenReturn(generateUserDtoList(userList));
        List<UserDto> result=service.getAllUsers();
        assertEquals(userDtoList,result);
        Mockito.verify(repository).findAll();
        Mockito.verify(converter).convert(userList);
    }

}
