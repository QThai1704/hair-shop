package t221124nqt.ecommerce.hair_shop.service.imp.file;

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
import t221124nqt.ecommerce.hair_shop.repository.auth.UserRepository;
import t221124nqt.ecommerce.hair_shop.service.file.UploadFileService;
import t221124nqt.ecommerce.hair_shop.util.format.FormattedDate;
import t221124nqt.ecommerce.hair_shop.util.format.FormattedString;

@Service
@RequiredArgsConstructor
public class IUploadFileService implements UploadFileService {
    private final UserRepository userRepository;

    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    @Override
    public List<User> getUsersDataFromExcel(InputStream inputStream) {
        List<User> users = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
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
        } catch (IOException e) {
            e.getStackTrace();
        }
        return users;
    }
}
