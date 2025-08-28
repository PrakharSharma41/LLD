class Producer {
    private final String queueName;
    private final QueueManager manager;
    private final String producerName;

    public Producer(String queueName, QueueManager manager, String producerName) {
        this.queueName = queueName;
        this.manager = manager;
        this.producerName = producerName;
    }

    public void publish(String content) {
        Message msg = new Message(producerName + " -> " + content);
        try {
            manager.getQueue(queueName).put(msg);
            System.out.println("Produced: " + msg.getContent() + " to queue " + queueName);
        } catch (InterruptedException e) {
        }
    }
}


// class Producer implements Runnable {
//     private final String queueName;
//     private final QueueManager manager;
//     private final String producerName;

//     public Producer(String queueName, QueueManager manager, String producerName) {
//         this.queueName = queueName;
//         this.manager = manager;
//         this.producerName = producerName;
//     }

//     @Override
//     public void run() {
//         try {
//             for (int i = 1; i <= 5; i++) {
//                 Message msg = new Message(producerName + " -> Message " + i);
//                 manager.getQueue(queueName).put(msg);
//                 System.out.println("Produced: " + msg.getContent() + " to queue " + queueName);
//                 Thread.sleep(500); // simulate work
//             }
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//         }
//     }
// }
