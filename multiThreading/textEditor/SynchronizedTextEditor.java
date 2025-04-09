package textEditor;
public class SynchronizedTextEditor {
    public static void main(String[] args) {
        RopeTextEditor editor = new RopeTextEditor("Hello, World!");

        Thread t1 = new Thread(() -> editor.insert(0, "A "));
        Thread t2 = new Thread(() -> editor.delete(7, 5));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final content: " + editor.getContents());
    }
    

}
