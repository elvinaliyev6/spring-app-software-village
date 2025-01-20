package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.UserRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.UserResponse;
import az.softwarevillage.book.model.User;
import az.softwarevillage.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse registerUser(@RequestBody UserRequest userRequest) {
        //dto => data transfer object
        return userService.registerUser(userRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
