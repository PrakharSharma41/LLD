public class Main {
    public static void main(String[] args) {
        StockPrice stockPrice=new StockPrice();
        stockPrice.update(10, 10);
        stockPrice.update(11, 100);
        System.out.println(stockPrice.getMaxPriceTill(20)); 
        stockPrice.update(20, 40);
        stockPrice.update(11, 10);
        // stockPrice.update(10, 100);

        System.out.println(stockPrice.getMaxPriceTill(20)); 
        System.out.println(stockPrice.getMaxPriceTillAfterAllUpdates(10)); 


    }
}