package mediatorDesign;

public class Bidder implements Colleague{
    String name;
    AuctionMediator auctionMediator;

    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this,bidAmount);
    }

    public Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator;
        auctionMediator.addBidder(this);
    }

    @Override
    public void receiveBidNotification(int bidAmount) {
        System.out.println();
    }

    @Override
    public String getName() {
        return name;
    }
    
}
