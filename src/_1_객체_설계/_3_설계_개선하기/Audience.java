package _1_객체_설계._3_설계_개선하기;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // 캡슐화
//    public Bag getBag() {
//        return bag;
//    }

    public Long buy(Ticket ticket) {
        // 가방 안에 초대장이 있는지 스스로 확인한다.
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        } else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}

