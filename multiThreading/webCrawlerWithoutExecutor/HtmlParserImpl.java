package webCrawlerWithoutExecutor;

import java.util.HashMap;

public class HtmlParserImpl implements HtmlParser{

    HashMap<String,String[]>map=new HashMap<>();
    HtmlParserImpl(){
        map.put("abc", new String[]{"abcd","def","ghi","a","b","c"});
        map.put("def",new String[]{"ghi","jkl"});
        map.put("jkl",new String[]{"ilm","mno"});
        map.put("mno",new String[]{"ilm","mno"});

    }
    public String[] getUrls(String url){
        return map.getOrDefault(url,new String[]{});
    }
}
