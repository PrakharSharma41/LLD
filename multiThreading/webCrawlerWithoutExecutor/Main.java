package webCrawlerWithoutExecutor;

public class Main {
    public static void main(String[] args) {
        Crawler crawler=new Crawler(5,new HtmlParserImpl());
        try{
            crawler.crawl("abc");
            Thread.sleep(10000);
            System.out.println("sleep finished");
            crawler.shutDown();
        }catch(Exception e){

        }
    }
}
