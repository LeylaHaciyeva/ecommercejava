package com.demo;

import demo.dto.UserDto;
import demo.dto.UserDtoConverter;
import demo.model.UserInformation;
import demo.repository.UserInformationRepository;
import demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends TestSupport {
    private UserInformationRepository repository;
    private UserService service;
    private UserDtoConverter converter;

    @BeforeAll
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
        Mockito.when(converter.convert(userList)).thenReturn(userDtoList);
        List<UserDto> result=service.getAllUsers();
        assertEquals(userDtoList,result);
        Mockito.verify(repository).findAll();
        Mockito.verify(converter).convert(userList);
    }
}
