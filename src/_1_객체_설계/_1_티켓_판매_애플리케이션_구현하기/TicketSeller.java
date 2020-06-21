package _1_객체_설계._1_티켓_판매_애플리케이션_구현하기;

// 초대장을 티켓으로 교환해 주거나 티켓 판매 역할하는 판매원.
public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }
}
