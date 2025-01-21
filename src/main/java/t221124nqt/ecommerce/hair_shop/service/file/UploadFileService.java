package t221124nqt.ecommerce.hair_shop.service.file;

import java.io.InputStream;
import java.util.List;

import t221124nqt.ecommerce.hair_shop.domain.auth.User;

public interface UploadFileService {
    List<User> getUsersDataFromExcel(InputStream inputStream);
}
