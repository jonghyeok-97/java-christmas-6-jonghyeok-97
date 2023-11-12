package christmas.Model;

import christmas.December;

public class VisitDate {
    private final int visitDate;

    public VisitDate(String inputDate) {
        validate(inputDate);
        this.visitDate = Integer.parseInt(inputDate);
    }

    public boolean isNormalDate() {
        return December.checkNormalDate(visitDate);
    }

    public int calculateNormalDiscount() {
        return visitDate * 100 + 900;
    }

    private void validate(String inputDate) throws IllegalArgumentException {
        validateNumber(inputDate);
        validateVisitDateRange(inputDate);
    }

    private void validateNumber(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateVisitDateRange(String inputDate) {
        int date = Integer.parseInt(inputDate);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
