package _9_유연한_설계._3_의존성_주입;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime) {
        this.title = title;
        this.runningTime = runningTime;
        // Service Locator 패턴
        this.discountPolicy = ServiceLocator.discountPolicy();
    }
}
