package t221124nqt.ecommerce.hair_shop.util.format;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FormattedDate {
    public static LocalDate convertFromDateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
