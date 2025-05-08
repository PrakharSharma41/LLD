package DesignPatterns.mediatorDesign;

public class Main {
    public static void main(String[] args) {
        AuctionMediator auctionMediatorObj=new Auction();
        Colleague bidder1=new Bidder("A", auctionMediatorObj);
        Colleague bidder2=new Bidder("B", auctionMediatorObj);
        bidder1.placeBid(121);
        bidder2.placeBid(432);
        bidder1.placeBid(2345432);
    }
}
