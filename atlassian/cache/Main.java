public class Main {
    public static void main(String[] args) {
        Cache<String, String> cache = new Cache<>(2, 2000, new DefaultExpiryPolicy());

        cache.put("A", "Apple");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cache.put("B", "Banana");

        System.out.println(cache.get("A")); // Apple
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(cache.get("A")); // null (expired)
        System.out.println(cache.get("B")); // Banana
        cache.put("C", "Mango");
        System.out.println(cache.get("C"));
        cache.put("D", "Grapes");
        System.out.println(cache.get("B"));

    }
}
