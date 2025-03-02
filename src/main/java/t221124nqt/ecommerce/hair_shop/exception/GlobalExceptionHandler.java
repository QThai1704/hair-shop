package t221124nqt.ecommerce.hair_shop.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import t221124nqt.ecommerce.hair_shop.component.TranslatorComponent;
import t221124nqt.ecommerce.hair_shop.dto.response.other.ResGlobalDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Custom exception
    @ExceptionHandler(value = {
        IdInvalidException.class,
        EmailException.class,
        CommonException.class,
        RuntimeException.class
    })
    public ResponseEntity<ResGlobalDTO<Object>> checkExists(Exception ex) {
        ResGlobalDTO<Object> res = new ResGlobalDTO<Object>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(TranslatorComponent.toLocale("error"));
        String message = ex.getMessage();
        int start = message.indexOf("messageTemplate=\'");
        int end = message.indexOf("\'}");
        String msgCode = message.substring(start + 16, end);
        res.setMessage(msgCode);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResGlobalDTO<Object>> validationErorr(
            MethodArgumentNotValidException ex) {
        ResGlobalDTO<Object> res = new ResGlobalDTO<Object>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(TranslatorComponent.toLocale("error"));
        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put(error.getField(), error.getDefaultMessage());
                    return errorDetails;
                })
                .collect(Collectors.toList());
        res.setMessage(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    // public String handleMessages(String e) {
    //      = e.
    //     return null;
    // }
}
