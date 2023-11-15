package christmas.view;

public class OutputViewMessage {
    public static final String WELCOME_GUIDE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String AMOUNT = "%s원\n";
    public static final String AMOUNT_DISCOUNT = "-%s원\n";
    public static final String NOTHING = "없음";

    // 이벤트 혜택 미리 보기
    public static final String PREVIEW_GUIDE_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    // 주문 메뉴
    public static final String ORDERED_MENU_GUIDE_MESSAGE = "<주문 메뉴>";
    public static final String MENU_AND_COUNT = "%s %d개\n";

    // 할인 전 총주문 금액
    public static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_GUIDE_MESSAGE = "<할인 전 총주문 금액>";

    // 증정 메뉴
    public static final String GIFT_MENU_GUIDE_MESSAGE = "<증정 메뉴>";
    public static final String ONE_GIFT = "샴페인 1개";

    // 혜택 내역
    public static final String BENEFITS_HISTORY_GUIDE_MESSAGE = "<혜택 내역>";
    public static final String AMOUNT_PER_DATE_DISCOUNT = "%s " + AMOUNT_DISCOUNT;
    public static final String GIFT_BENEFIT = "증정 이벤트: " + AMOUNT_DISCOUNT;

    // 총혜택 금액
    public static final String TOTAL_BENEFIT_AMOUNT_GUIDE_MESSAGE = "<총혜택 금액>";

    // 할인 후 예상 결제 금액
    public static final String EXPECTED_AMOUNT_AFTER_BENEFIT_GUIDE_MESSAGE = "<할인 후 예상 결제 금액>";

    // 12월 이벤트 배지
    public static final String BADGE_GUIDE_MESSAGE = "<12월 이벤트 배지>";
    public static final String BADGE_STAR = "별";
    public static final String BADGE_TREE = "트리";
    public static final String BADGE_SANTA = "산타";

    // 12월 이벤트 배지에 대한 최소 주문 금액
    public static final int MIN_AMOUNT_OF_STAR = 5000;
    public static final int MIN_AMOUNT_OF_TREE = 10000;
    public static final int MIN_AMOUNT_OF_SANTA = 20000;
}