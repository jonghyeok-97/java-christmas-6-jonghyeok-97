package christmas.model;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        this.date = date;
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
}