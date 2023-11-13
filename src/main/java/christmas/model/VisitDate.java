package christmas.model;

public class VisitDate {
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
