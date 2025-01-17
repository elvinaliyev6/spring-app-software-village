package az.softwarevillage.book.service;

import az.softwarevillage.book.enums.EnumAvailableStatus;
import az.softwarevillage.book.model.User;
import az.softwarevillage.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String registerUser(User user) {
        List<User> users =getAllUsers();

        for(User u : users) {
            if(u.getEmail().equals(user.getEmail())) {
                System.err.println("Email already registered");
                return "Email already registered";
            }

            if(u.getUsername().equals(user.getUsername())) {
                System.err.println("Username already registered");
                return "Username already registered";
            }
        }

        userRepository.save(user);
        return "success";
    }

    public List<User> getAllUsers() {
        return userRepository
                .findAllByStatus(EnumAvailableStatus.ACTIVE.getValue());
    }
}
