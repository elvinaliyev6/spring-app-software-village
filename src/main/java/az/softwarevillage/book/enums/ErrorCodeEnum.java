package az.softwarevillage.book.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    EMAIL_ALREADY_EXIST_ERROR("Email already exists",10),
    USERNAME_ALREADY_EXIST_ERROR("Username already exists",11),
    USER_NOT_FOUND_ERROR("User not Found", 12),
    UNKNOWN_ERROR("Unknown Error", 13),
    BOOK_ALREADY_EXIST_ERROR("Book Already exist", 21);

    private String message;
    private int code;

    ErrorCodeEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
