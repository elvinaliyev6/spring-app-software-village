package az.softwarevillage.book.controller;

import az.softwarevillage.book.model.User;
import az.softwarevillage.book.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/insertUser")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
