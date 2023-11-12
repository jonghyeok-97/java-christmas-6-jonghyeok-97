package christmas.Model;

public class PresentDiscount {
    private boolean isPresentDiscount;
    private int presentDiscountPrice;
    private boolean hasChampagne;

    public PresentDiscount(Order order) {
        if (order.isOverMinPresentPrice() && order.isOverMinDiscountPrice()) {
            this.isPresentDiscount = true;
            this.presentDiscountPrice = 25000;
            this.hasChampagne = order.hasChampagneOfMenu();
        }
    }

    public boolean getPresentDiscount() {
        return isPresentDiscount;
    }
}
