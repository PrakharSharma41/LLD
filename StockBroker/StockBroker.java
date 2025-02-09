package StockBroker;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

enum OrderType {
    BUY,
    SELL
}

enum OrderStatus {
    PENDING,
    PARTIALLY_FILLED,
    FILLED
}

class Order {
    private String orderId;
    private double price;
    private int volume;
    private OrderType orderType;
    private OrderStatus status;

    public Order(String orderId, double price, int volume, OrderType orderType) {
        this.orderId = orderId;
        this.price = price;
        this.volume = volume;
        this.orderType = orderType;
        this.status = OrderStatus.PENDING;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", price=" + price + ", volume=" + volume + ", orderType=" + orderType + ", status=" + status + "]";
    }

    public String getOrderId() { return orderId; }
    public Double getPrice() { return price; }
    public int getVolume() { return volume; }
    public void setVolume(int volume) { 
        this.volume = volume; 
        if (this.volume == 0) {
            this.status = OrderStatus.FILLED;
        } else {
            this.status = OrderStatus.PARTIALLY_FILLED;
        }
    }
    public OrderType getOrderType() { return orderType; }
    public OrderStatus getStatus() { return status; }
}

class PriceLevel {
    private double limitPrice;
    private List<Order> orders;
    
    public PriceLevel(double limitPrice) {
        this.limitPrice = limitPrice;
        this.orders = new LinkedList<>();
    }
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() { return orders; }
    public double getLimitPrice() { return limitPrice; }
}

class Book {
    private TreeMap<Double, PriceLevel> priceLevels;
    private OrderType orderType;
    
    public Book(OrderType orderType) {
        this.orderType = orderType;
        this.priceLevels = new TreeMap<>(orderType == OrderType.BUY ? Collections.reverseOrder() : Comparator.naturalOrder());
    }
    
    public Map<Double, PriceLevel> getPriceLevels() {
        return priceLevels;
    }

    public void addOrder(Order order) {
        priceLevels.computeIfAbsent(order.getPrice(), PriceLevel::new).addOrder(order);
    }
    
    public void removeOrder(Order order) {
        PriceLevel level = priceLevels.get(order.getPrice());
        if (level != null) {
            level.removeOrder(order);
            if (level.getOrders().isEmpty()) {
                priceLevels.remove(order.getPrice());
            }
        }
    }
    
    public PriceLevel getBestPriceLevel() {
        return priceLevels.isEmpty() ? null : priceLevels.firstEntry().getValue();
    }
}

class Stock {
    private String stockName;
    private OrderBook orderBook;
    private final ReentrantLock lock = new ReentrantLock();
    
    public Stock(String stockName) {
        this.stockName = stockName;
        this.orderBook = new OrderBook();
    }

    public void placeOrder(Order order) {
        lock.lock();
        try {
            orderBook.placeOrder(order);
        } finally {
            lock.unlock();
        }
    }
    
    public void cancelOrder(String orderId) {
        lock.lock();
        try {
            orderBook.cancelOrder(orderId);
        } finally {
            lock.unlock();
        }
    }
}

class OrderBook {
    private Book buyBook;
    private Book sellBook;
    private Map<String, Order> orderMap;
    
    public OrderBook() {
        this.buyBook = new Book(OrderType.BUY);
        this.sellBook = new Book(OrderType.SELL);
        this.orderMap = new HashMap<>();
    }
    
    public void placeOrder(Order order) {
        if (order.getOrderType() == OrderType.BUY) {
            buyBook.addOrder(order);
        } else {
            sellBook.addOrder(order);
        }
        orderMap.put(order.getOrderId(), order);
        matchOrders(order);
    }
    
    public void cancelOrder(String orderId) {
        Order order = orderMap.remove(orderId);
        if (order != null) {
            if (order.getOrderType() == OrderType.BUY) {
                buyBook.removeOrder(order);
            } else {
                sellBook.removeOrder(order);
            }
        }
    }
    
    private void matchOrders(Order order) {
        Book oppositeBook;
        if(order.getOrderType() == OrderType.BUY)oppositeBook=sellBook;
        else oppositeBook=buyBook;
        while (!oppositeBook.getPriceLevels().isEmpty() && order.getVolume() > 0) {
            PriceLevel bestLevel = oppositeBook.getBestPriceLevel();
            if (order.getOrderType() == OrderType.BUY && bestLevel.getLimitPrice() > order.getPrice()) break;
            if (order.getOrderType() == OrderType.SELL && bestLevel.getLimitPrice() < order.getPrice()) break;
            
            Iterator<Order> iterator = bestLevel.getOrders().iterator();
            while (iterator.hasNext() && order.getVolume() > 0) {
                Order matchedOrder = iterator.next();
                if (matchedOrder.getStatus() == OrderStatus.FILLED) {
                    iterator.remove();
                    orderMap.remove(matchedOrder.getOrderId());
                    continue;
                }
                int tradeVolume = Math.min(order.getVolume(), matchedOrder.getVolume());
                order.setVolume(order.getVolume() - tradeVolume);
                matchedOrder.setVolume(matchedOrder.getVolume() - tradeVolume);
                System.out.println("Trade executed: " + tradeVolume + " units at " + bestLevel.getLimitPrice());

                if(matchedOrder.getVolume()==0){
                    matchedOrder.setStatus(OrderStatus.FILLED);
                    orderMap.remove(matchedOrder.getOrderId());
                }else{
                    matchedOrder.setStatus(OrderStatus.PARTIALLY_FILLED);
                }
                if(order.getVolume()==0){
                    order.setStatus(OrderStatus.FILLED);
                    break;
                }
            }
            
            if (bestLevel.getOrders().isEmpty()) {
                oppositeBook.getPriceLevels().remove(bestLevel.getLimitPrice());
            }
        }
        if (order.getStatus() == OrderStatus.FILLED) {
            orderMap.remove(order.getOrderId());
            System.out.println(order + " order fully matched.");
        } else if(order.getStatus() == OrderStatus.PARTIALLY_FILLED){
            System.out.println(order + " order partially matched.");
        }else{
            System.out.println(order+" not matched");
        }
    }
}

public class StockBroker {
    public static void main(String[] args) {
        Stock appleStock = new Stock("Apple");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            int orderId = i;
            executor.submit(() -> {
                Order buyOrder = new Order(String.valueOf(orderId), 100, 10, OrderType.BUY);
                appleStock.placeOrder(buyOrder);
            });
        }
        for (int i = 11; i <= 15; i++) {
            int orderId = i;
            executor.submit(() -> {
                Order buyOrder = new Order(String.valueOf(orderId), 100, 10, OrderType.SELL);
                appleStock.placeOrder(buyOrder);
            });
        }

        executor.shutdown();
    }
}
