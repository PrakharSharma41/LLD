package StampedLock.optimisticRead;

public class Test {
    public static void main(String[] args) {
        ConfigCache cache = new ConfigCache();
        cache.updateConfig("theme", "dark");

        Runnable reader = () -> {
            while (true) {
                String theme = cache.getConfig("theme");
                System.out.println(Thread.currentThread().getName() + " read: " + theme);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };

        Runnable writer = () -> {
            String[] themes = {"dark", "light", "blue"};
            int i = 0;
            while (true) {
                cache.updateConfig("theme", themes[i % themes.length]);
                i++;
                try { Thread.sleep(3000); } catch (InterruptedException e) {}
            }
        };

        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(writer, "Writer").start();
    }
}
