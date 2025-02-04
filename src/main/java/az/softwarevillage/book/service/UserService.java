package az.softwarevillage.book.service;

import az.softwarevillage.book.dto.request.UserRequest;
import az.softwarevillage.book.dto.response.BaseResponse;
import az.softwarevillage.book.dto.response.UserResponse;
import az.softwarevillage.book.enums.EnumAvailableStatus;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import az.softwarevillage.book.exception.UserExistsException;
import az.softwarevillage.book.exception.UserNotFoundExeption;
import az.softwarevillage.book.model.User;
import az.softwarevillage.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
private String name;
    private final UserRepository userRepository;

    public BaseResponse registerUser(UserRequest userRequest) {
        checkUsernameAndPassword(userRequest);

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .role(userRequest.getRole())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .username(userRequest.getUsername())
                .status(EnumAvailableStatus.ACTIVE.getValue())
                .build();

        userRepository.save(user);

        return BaseResponse.getSuccessMessage();
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository
                .findAllByStatus(EnumAvailableStatus.ACTIVE.getValue());
        return getUserResponseList(users);
    }

    public UserResponse getUsersById(Long id) {
        User user = checkUser(id);
        return mapEntityToResponse(user);

    }

    public BaseResponse updateUser(Long id, UserRequest userRequest) {

        User user = checkUser(id);
        user = User.builder()
                .id(id)
                .firstName(userRequest.getFirstName())
                .role(userRequest.getRole())
                .lastName(userRequest.getLastName())
                .password(userRequest.getPassword())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .status(EnumAvailableStatus.ACTIVE.getValue())
                .build();

        userRepository.save(user);
        return BaseResponse.getSuccessMessage();
    }

    private User checkUser(Long id) {
        User user = userRepository.findByIdAndStatus(id, EnumAvailableStatus.ACTIVE.getValue());

        if (user == null) {
            throw new UserNotFoundExeption(ErrorCodeEnum.USER_NOT_FOUND_ERROR.getMessage());
        }
        return user;
    }


    private void checkUsernameAndPassword(UserRequest userRequest) {
        List<UserResponse> users = getAllUsers();

        for (UserResponse u : users) {
            if (u.getEmail().equals(userRequest.getEmail())) {
                throw new UserExistsException(ErrorCodeEnum.EMAIL_ALREADY_EXIST_ERROR.getMessage());
            }

            if (u.getUsername().equals(userRequest.getUsername())) {
                throw new UserExistsException(ErrorCodeEnum.USERNAME_ALREADY_EXIST_ERROR.getMessage());
            }
        }
    }

    private UserResponse mapEntityToResponse(User user) {
        return UserResponse.builder().email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .phone(user.getPhone())
                .password(user.getPassword())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }


    private List<UserResponse> getUserResponseList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();

        for (User u : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(u.getId());
            userResponse.setEmail(u.getEmail());
            userResponse.setUsername(u.getUsername());
            userResponse.setFirstName(u.getFirstName());
            userResponse.setLastName(u.getLastName());
            userResponse.setPhone(u.getPhone());
            userResponse.setRole(u.getRole());
            userResponse.setPassword(u.getPassword());

            userResponses.add(userResponse);
        }
        return userResponses;
    }
}
