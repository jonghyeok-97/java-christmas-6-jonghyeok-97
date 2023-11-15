package christmas.model;

public class VisitDateValidator {
    private static final int FIRST_DATE_OF_DECEMBER = 1;
    private static final int LAST_DATE_OF_DECEMBER = 31;
    private static final String ERROR_RETRY_INPUT = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void validateDate(String inputDate) throws IllegalArgumentException {
        validateNumber(inputDate);
        validateDateRange(inputDate);
    }

    private void validateNumber(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_RETRY_INPUT);
        }
    }

    private void validateDateRange(String inputDate) {
        int date = Integer.parseInt(inputDate);
        if (date < FIRST_DATE_OF_DECEMBER || date > LAST_DATE_OF_DECEMBER) {
            throw new IllegalArgumentException(ERROR_RETRY_INPUT);
        }
    }
}