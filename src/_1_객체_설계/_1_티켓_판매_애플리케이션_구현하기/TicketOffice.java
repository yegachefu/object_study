package _1_객체_설계._1_티켓_판매_애플리케이션_구현하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;                                    // 판매 금액
    private List<Ticket> tickets = new ArrayList<>();       // 판매하거나 교환해 줄 티켓의 목록

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    // 티켓 판매
    public Ticket getTicket() {
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}
