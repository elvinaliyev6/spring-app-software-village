package az.softwarevillage.book.exception;

import az.softwarevillage.book.dto.response.ErrorResponse;
import az.softwarevillage.book.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleUserNotFoundExeption(UserNotFoundExeption e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCodeEnum.USER_NOT_FOUND_ERROR.getCode())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleNumberFormatException(MethodArgumentTypeMismatchException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleException(Exception e) {
        e.printStackTrace();
        return ErrorResponse.builder()
                .message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage())
                .errorCode(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(BookAlreadyExist.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    ErrorResponse handleBookAlreadyExist(BookAlreadyExist e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(ErrorCodeEnum.BOOK_ALREADY_EXIST_ERROR.getCode());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.ALREADY_REPORTED.value());
        return errorResponse;
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleBookNotFoundException(BookNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(ErrorCodeEnum.BOOK_NOT_FOUND_ERROR.getCode());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return errorResponse;
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse handleAuthorNotFoundException(AuthorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(ErrorCodeEnum.AUTHOR_NOT_FOUND_ERROR.getCode());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return errorResponse;
    }


}
