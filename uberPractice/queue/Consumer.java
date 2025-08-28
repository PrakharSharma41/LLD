class Consumer {
    private final String queueName;
    private final QueueManager manager;
    private final String consumerName;

    public Consumer(String queueName, QueueManager manager, String consumerName) {
        this.queueName = queueName;
        this.manager = manager;
        this.consumerName = consumerName;
    }


    public void consume(){
        Message msg;
        try {
            msg = manager.getQueue(queueName).take();
            System.out.println("Consumed by " + consumerName + " from " + queueName + ": " + msg.getContent());
        } catch (InterruptedException e) {
        } 
    }
}


// class Consumer implements Runnable {
//     private final String queueName;
//     private final QueueManager manager;
//     private final String consumerName;

//     public Consumer(String queueName, QueueManager manager, String consumerName) {
//         this.queueName = queueName;
//         this.manager = manager;
//         this.consumerName = consumerName;
//     }

//     @Override
//     public void run() {
//         try {
//             while (true) { // keep consuming
//                 Message msg = manager.getQueue(queueName).take(); // blocking
//                 System.out.println("Consumed by " + consumerName + " from " + queueName + ": " + msg.getContent());
//                 Thread.sleep(1000); // simulate processing
//             }
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }
//     }
// }