package _1_객체_설계._1_티켓_판매_애플리케이션_구현하기;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    // 소극장이 관람객을 맞이하는 메서드
    public void enter(Audience audience) {
        // 관람객 가방에 초대장 있는지?
        if (audience.getBag().hasInvitation()) {
            // 판매원이 티켓 하나 관람객한테 준다.
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            // 판매원이 티켓 하나 관람객한테 주고 관람객 돈 빼서 판매원에게 준다.
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
