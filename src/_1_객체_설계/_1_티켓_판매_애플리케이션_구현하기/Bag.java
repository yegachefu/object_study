package _1_객체_설계._1_티켓_판매_애플리케이션_구현하기;

public class Bag {
    private Long amount;            // 현금
    private Invitation invitation;  // 티켓으로 교환할 수 있는 초대장
    private Ticket ticket;

    // 이벤트에 당첨되지 않은 관람객의 가방에는 초대장이 들어있지 않다.
    public Bag(Long amount) {
        this(null, amount);
    }

    // 이벤트에 당첨된 관람객의 가방에는 초대장이 있다.
    public Bag(Invitation invitation, Long amount) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation() {
        return ticket != null;
    }

    public boolean hasTicket() {
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
