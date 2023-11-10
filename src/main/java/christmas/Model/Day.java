package christmas.Model;

public class Day {
    //private final int visitDate;

    public Day(String inputDate) {
        validate(inputDate);
    }

    private void validate(String inputDate) {
        validateNumber(inputDate);
        //validateVisitDateRange(inputDate);
    }

    private void validateNumber(String inputDate) {
        try {
            Integer.parseInt(inputDate);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

}
