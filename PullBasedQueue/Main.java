package PullBasedQueue;

public class Main {
    public static void main(String[] args) {
        InMemoryQueue inMemoryQueue=new InMemoryQueue(2000L);
        Publisher publisher1=new Publisher(inMemoryQueue);
        Consumer consumer1=new Consumer(inMemoryQueue);
        Consumer consumer2=new Consumer(inMemoryQueue);
        Thread thread1=new Thread(consumer1);
        Thread thread2=new Thread(consumer2);
        Thread thread3=new Thread(publisher1);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
