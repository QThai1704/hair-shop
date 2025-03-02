package t221124nqt.ecommerce.hair_shop.service.file;

import java.io.InputStream;
import java.util.List;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.product.Product;

public interface ImportFilesService {
    List<User> importFileUserToDb(InputStream inputStream, String sheetName);
    List<Product> importFileProductToDb(InputStream inputStream, String sheetName);
}
