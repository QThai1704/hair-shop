package t221124nqt.ecommerce.hair_shop.controller.other;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.service.auth.IUserService;
import t221124nqt.ecommerce.hair_shop.service.product.IProductService;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ImportFilesController {
    private final IUserService userService;
    private final IProductService productService;

    @PostMapping("/convert-file-csv-to-db")
    @ApiMessage(message = "Chuyển dữ liệu từ file excel vào cơ sở dữ liệu")
    public ResponseEntity<String> importUserToDB(@RequestParam("file") MultipartFile file) {
        this.userService.saveUsersToDatabase(file);
        return ResponseEntity.ok().body("Hoàn thành chuyển dữ liệu");
    }

    @PostMapping("/import-file-product-to-db")
    @ApiMessage(message = "Chuyển dữ liệu từ file excel vào cơ sở dữ liệu")
    public ResponseEntity<String> importProductToDB(@RequestParam("file") MultipartFile file) {
        this.productService.saveProductsToDatabase(file);
        return ResponseEntity.ok().body("Hoàn thành chuyển dữ liệu");
    }

}
