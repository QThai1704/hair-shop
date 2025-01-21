package t221124nqt.ecommerce.hair_shop.controller.file;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.service.imp.auth.IUserService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UploadFileController {
    private final IUserService userService;

    @PostMapping("/convert-file-csv-to-db")
    @ApiMessage(message = "Chuyển dữ liệu từ file excel vào cơ sở dữ liệu")
    public ResponseEntity<String> postMethodName(@RequestParam("file") MultipartFile file) {
        this.userService.saveUsersToDatabase(file);
        return ResponseEntity.ok().body("Hoàn thành chuyển dữ liệu");
    }

}
