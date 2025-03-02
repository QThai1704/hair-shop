package t221124nqt.ecommerce.hair_shop.service.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.domain.product.Product;
import t221124nqt.ecommerce.hair_shop.util.format.FormattedDate;
import t221124nqt.ecommerce.hair_shop.util.format.FormattedString;

@Service
@RequiredArgsConstructor
public class ImpImportFilesService implements ImportFilesService {

    // Kiểm tra upload file
    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public XSSFSheet readSheet(InputStream inputStream, String sheetName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return sheet;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> importFileUserToDb(InputStream inputStream, String sheetName) {
        List<User> users = new ArrayList<>();
        XSSFSheet sheet = readSheet(inputStream, sheetName);
        int rowIndex = 0;
        for (Row row : sheet) {
            if (rowIndex == 0) {
                rowIndex++;
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIndex = 0;
            User user = new User();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cellIndex) {
                    case 0 -> user.setUsername(cell.getStringCellValue());
                    case 1 -> user.setLastName(cell.getStringCellValue());
                    case 2 -> user.setFirstName(cell.getStringCellValue());
                    case 3 -> user.setGender(FormattedString.convertStringToGenderEnum(cell.getStringCellValue()));
                    case 4 -> user.setEmail(cell.getStringCellValue());
                    case 5 ->
                        user.setDateOfBirth(FormattedDate.convertFromDateToLocalDate(cell.getDateCellValue()));
                    case 6 -> {
                        DataFormatter dataFormatter = new DataFormatter();
                        String codeValue = dataFormatter.formatCellValue(cell);
                        user.setCode(codeValue);
                    }
                    case 7 -> user.setJobTitle(cell.getStringCellValue());
                    case 8 -> user.setDepartment(cell.getStringCellValue());
                    case 9 -> {
                        DataFormatter dataFormatter = new DataFormatter();
                        String phoneValue = "0" + dataFormatter.formatCellValue(cell);
                        user.setPhone(phoneValue);
                    }
                    case 10 -> user.setAddress(cell.getStringCellValue());
                    case 11 -> user.setCity(cell.getStringCellValue());
                    case 12 -> user.setState(cell.getStringCellValue());
                    case 13 -> user.setStatus(FormattedString.convertStringToStatusEnum(cell.getStringCellValue()));
                    default -> {
                    }
                }
                cellIndex++;
            }
            users.add(user);
        }
        return users;
    }

    @Override
    public List<Product> importFileProductToDb(InputStream inputStream, String sheetName) {
        List<Product> products = new ArrayList<>();
        XSSFSheet sheet = readSheet(inputStream, sheetName);
        int rowIndex = 0;
        for (Row row : sheet) {
            if (rowIndex == 0) {
                rowIndex++;
                continue;
            }
            Iterator<Cell> cellIterator = row.iterator();
            int cellIndex = 0;
            Product product = new Product();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cellIndex) {
                    case 0 -> product.setProductCode(cell.getStringCellValue());
                    case 1 -> product.setProductName(cell.getStringCellValue());
                    case 2 -> product.setImage(cell.getStringCellValue());
                    case 3 -> product.setShortDescription(cell.getStringCellValue());
                    case 4 -> {
                        product.setDescription(cell.getStringCellValue());
                    }
                    case 5 -> {
                        product.setStandardCost(cell.getNumericCellValue());
                    }
                    case 6 -> product.setListPrice(cell.getNumericCellValue());
                    case 7 -> product.setQuantityPerUnit(cell.getNumericCellValue());
                    case 8 -> product.setFeatured(cell.getBooleanCellValue());
                    case 9 -> product.setNew(cell.getBooleanCellValue());
                    default -> {}
                }
                cellIndex++;
            }
            products.add(product);
        }
        return products;
    }
}
