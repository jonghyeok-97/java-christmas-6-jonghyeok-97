package christmas.model;

public class VisitDate {
    private static final int FIRST_DATE_OF_DECEMBER = 1;
    private static final int LAST_DATE_OF_DECEMBER = 31;
    private static final String ERROR_INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final int date;

    public VisitDate(String inputDate) {
        validate(inputDate);
        this.date = Integer.parseInt(inputDate);
    }

    public int getDate() {
        return date;
    }

    public boolean isNormalDate() {
        return December.checkNormalDate(date);
    }

    public boolean isWeekdaysDate() {
        return December.checkWeekdaysDate(date);
    }

    public boolean isWeekendDate() {
        return December.checkWeekendDate(date);
    }

    public boolean isSpecialDate() {
        return December.checkSpecialDate(date);
    }

    public int calculateNormalDiscount() {
        return date * 100 + 900;
    }

    private void validate(String inputDate) throws IllegalArgumentException {
        validateNumber(inputDate);
        validateDateRange(inputDate);
    }

    private void validateNumber(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

    private void validateDateRange(String inputDate) {
        int date = Integer.parseInt(inputDate);
        if (date < FIRST_DATE_OF_DECEMBER || date > LAST_DATE_OF_DECEMBER) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }
}