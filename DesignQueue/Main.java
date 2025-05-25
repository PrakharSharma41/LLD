public class Main{
    public static void main(String[] args) {
        // design a queue using array and some buffer variables only
        Queue queue=new Queue(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        // System.out.println(queue.remove());
        
        System.out.println(queue.remove());
        queue.add(5);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());


    }
}