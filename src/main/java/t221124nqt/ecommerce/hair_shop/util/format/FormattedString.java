package t221124nqt.ecommerce.hair_shop.util.format;

import t221124nqt.ecommerce.hair_shop.constant.GenderEnum;
import t221124nqt.ecommerce.hair_shop.constant.StatusEnum;

public class FormattedString {
    public static GenderEnum convertStringToGenderEnum(String gender) {
        switch (gender) {
            case "FEMALE":
                return GenderEnum.FEMALE;
            case "MALE":
                return GenderEnum.MALE;
            case "OTHER":
                return GenderEnum.OTHER;
            default:
                break;
        }
        return null;
    }

    public static StatusEnum convertStringToStatusEnum(String status) {
        switch (status) {
            case "ACTIVE":
                return StatusEnum.ACTIVE;
            case "INACTIVE":
                return StatusEnum.INACTIVE;
            case "BANNED":
                return StatusEnum.BANNED;
            default:
                break;
        }
        return null;
    }
}
