package webCrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Crawler {
    private ExecutorService executor;
    private Set<String> visitedUrls;
    private List<Future<?>> futures;

    public Crawler() {
        this.executor = Executors.newFixedThreadPool(10);
        this.visitedUrls = ConcurrentHashMap.newKeySet();
        this.futures = new CopyOnWriteArrayList<>();
    }

    public List<String> crawl(HtmlParser htmlParser, String startUrl) {
        String hostName = getHostName(startUrl);
        if (visitedUrls.add(startUrl)) {
            futures.add(executor.submit(() -> crawlUrl(startUrl, htmlParser, hostName)));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    
        executor.shutdown();
        return new ArrayList<>(visitedUrls);
    }

    private void crawlUrl(String url, HtmlParser htmlParser, String hostName) {
        for (String nextUrl : htmlParser.getUrls(url)) {
            if (getHostName(nextUrl).equals(hostName) && visitedUrls.add(nextUrl)) {
                futures.add(executor.submit(() -> crawlUrl(nextUrl, htmlParser, hostName)));
            }
        }
         // dont use future here because it makes the worker thread wait
        // for (Future<?> future : futures) {
        //     try {
        //         future.get(); // blocks until task completes
        //     } catch (InterruptedException | ExecutionException e) {
        //         e.printStackTrace();
        //     }
        // }        
    }

    private String getHostName(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
