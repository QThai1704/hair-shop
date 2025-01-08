package t221124nqt.ecommerce.hair_shop.util.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import t221124nqt.ecommerce.hair_shop.dto.response.other.ResGlobalDTO;
import t221124nqt.ecommerce.hair_shop.util.exception.CommonException;
import t221124nqt.ecommerce.hair_shop.util.exception.EmailException;
import t221124nqt.ecommerce.hair_shop.util.exception.IdInvalidException;

@ControllerAdvice
public class FormattedException {
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
        res.setError("Dữ liệu không hợp lệ, vui lòng kiểm tra lại!");
        res.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResGlobalDTO<Object>> validationErorr(
            MethodArgumentNotValidException ex) {
        ResGlobalDTO<Object> res = new ResGlobalDTO<Object>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError("Dữ liệu không hợp lệ, vui lòng kiểm tra lại!");
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
}
