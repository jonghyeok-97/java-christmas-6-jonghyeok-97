package christmas.view;

public class OutputViewMessage {
    public static final String WELCOME = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    // 이벤트 혜택 미리 보기
    public static final String PREVIEW_GUIDE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    // 주문 메뉴
    public static final String ORDERED_MENU = "<주문 메뉴>";
    public static final String MENU_AND_COUNT = "%s %d개\n";

    // 할인 전 총주문 금액
    public static final String TOTAL_AMOUNT_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    public static final String AMOUNT = "%s원\n";

    // 증정 메뉴
    public static final String GIFT_MENU = "<증정 메뉴>";
    public static final String NOTHING = "없음";
    public static final String ONE_GIFT = "샴페인 1개";

    // 혜택 내역
    public static final String BENEFITS_HISTORY = "<혜택 내역>";
    public static final String AMOUNT_PER_BENEFIT = "%s -%s원\n";
    public static final String GIFT_BENEFIT = "증정 이벤트: -%s원\n";

    // 총혜택 금액
    public static final String TOTAL_BENEFIT_AMOUNT = "<총혜택 금액>";
    public static final String AMOUNT_DISCOUNT = "-%s원\n";

    // 할인 후 예상 결제 금액
    public static final String EXPECTED_AMOUNT_AFTER_BENEFIT = "<할인 후 예상 결제 금액>";
    public static final String AMOUNT_RESULT = "%s원\n";

    // 12월 이벤트 배지
    public static final String BADGE = "<12월 이벤트 배지>";
    public static final String BADGE_STAR = "별";
    public static final String BADGE_TREE = "트리";
    public static final String BADGE_SANTA = "산타";

    // 12월 이벤트 배지에 대한 최소 주문 금액
    public static final int MIN_AMOUNT_OF_STAR = 5000;
    public static final int MIN_AMOUNT_OF_TREE = 10000;
    public static final int MIN_AMOUNT_OF_SANTA = 20000;
}