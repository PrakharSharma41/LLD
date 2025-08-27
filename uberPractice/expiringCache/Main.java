public class Main {
    public static void main(String[] args) {
        ExpiringCache kv=new ExpiringCache(5000);

        kv.put("a", 10, 1000);
        kv.put("b", 20, 2000);
        kv.put("c", 30, 3000);

        System.out.println(kv.get("a", 3500)); // 10
        System.out.println(kv.getAverage(3500)); // (10+20+30)/3 = 20.0

        // after 7000 ms, "a" expired (1000+5000=6000 < 7000)
        System.out.println(kv.get("a", 7000)); // null
        System.out.println(kv.get("b", 7000));
        System.out.println(kv.getAverage(6000)); // (20+30)/2 = 25.0        
    }
}
