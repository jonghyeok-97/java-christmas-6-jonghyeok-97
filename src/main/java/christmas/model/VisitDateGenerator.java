package christmas.model;

public class VisitDateGenerator {
    private final VisitDateValidator visitDateValidator;

    public VisitDateGenerator() {
        this.visitDateValidator = new VisitDateValidator();
    }

    public VisitDate createDate(String input) throws IllegalArgumentException {
        visitDateValidator.validateDate(input);
        return new VisitDate(Integer.parseInt(input));
    }
}