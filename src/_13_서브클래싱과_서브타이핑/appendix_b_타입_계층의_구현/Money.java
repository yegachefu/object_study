package _13_서브클래싱과_서브타이핑.appendix_b_타입_계층의_구현;

public class Money {
    public static final Money ZERO = new Money(0L);

    private long money;

    public Money(long money) {
        this.money = money;
    }

    public Money times(long seconds) {
        return new Money(money * seconds);
    }

    public Money plus(Money m) {
        this.money += m.money;
        return this;
    }

    public Money minus(Money m) {
        this.money -= m.money;
        return this;
    }
}
