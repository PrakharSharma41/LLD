package webCrawlerWithoutExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Crawler {
    private Set<String>visitedUrls;
    private List<Thread>threads;
    private Deque<String>taskQueue;
    private HtmlParser htmlParser;
    private volatile boolean isRunning=true;
    Crawler(int threadCount,HtmlParser htmlParser){
        this.htmlParser=htmlParser;
        taskQueue=new LinkedList<>();
        visitedUrls=new HashSet<>();
        threads=new ArrayList<>();
        for(int i=0;i<threadCount;i++){
            Thread worker=new Thread(()->{
                while(isRunning|| !taskQueue.isEmpty()){
                    String url=null;
                    String hostName=null;
                    synchronized(taskQueue){
                        try{
                            while(taskQueue.isEmpty()){
                                taskQueue.wait();
                            }
                            if(!taskQueue.isEmpty()){
                                url = taskQueue.pollFirst();
                                hostName = getHostName(url);
                                // visitedUrls.add(url);
                            }
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                    if(url!=null&&hostName!=null){
                        crawlUrlTask(url, hostName);
                    }
                }
            });
            threads.add(worker);
            worker.start();
        }
    }
    public void crawl(String startUrl){
        synchronized(taskQueue){
            if (visitedUrls.add(startUrl)) {
                taskQueue.addLast(startUrl);
                taskQueue.notifyAll();
            }
        }
    }
    public void crawlUrlTask(String url, String hostName) {
        System.out.println("here1 "+Thread.currentThread().getName()+" has started crawling "+url); 
        for (String nextUrl : htmlParser.getUrls(url)) {
            if(getHostName(nextUrl).equals(hostName)){
                synchronized(taskQueue){
                    if(visitedUrls.add(nextUrl)){
                        taskQueue.addLast(nextUrl);
                        taskQueue.notifyAll();
                    }
                }
                try{
                    Thread.sleep(1000);
                }catch(Exception e){
                }                
            }
        }            
        try{
            Thread.sleep(2000);
        }catch(Exception e){
        }
        System.out.println("here1 "+Thread.currentThread().getName()+" has crawled "+url); 
    }   
    public void shutDown(){
        isRunning=false;
        synchronized (taskQueue) {
            taskQueue.notifyAll(); // wake up waiting threads to exit
        }
        for (Thread t : threads) {
            try {
                t.interrupt();
                // t.join(); // wait for threads to finish
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   
    }
    private String getHostName(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }    
}
