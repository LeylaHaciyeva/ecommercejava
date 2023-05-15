package demo.controller;

import demo.dto.CreateUserRequest;
import demo.dto.UpdateUserRequest;
import demo.dto.UserDto;
import demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest){
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody UpdateUserRequest updateUserRequest){
        return ResponseEntity.ok(userService.updateUser(id,updateUserRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deActivedUser(@PathVariable("id") Long id){
        userService.deActiveUser(id);
        return ResponseEntity.ok().build();
    }
//    @PatchMapping("/{id}")
//    public ResponseEntity<Void> activedUser(@PathVariable("id") Long id){
//        userService.activeUser(id);
//        return ResponseEntity.ok().build();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
