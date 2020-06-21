package _1_객체_설계._1_티켓_판매_애플리케이션_구현하기;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }
}

