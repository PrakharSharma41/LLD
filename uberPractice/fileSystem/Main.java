package uberPractice.fileSystem;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        FileSystem fs=new FileSystem();
        new Thread(()->fs.mkdir("/abc/cd/ef")).start();
        // new Thread(()->fs.cd("./abc")).start();        
        new Thread(()->fs.cd("/abc/./cd")).start();
        try{
            Thread.sleep(2000);
        }catch(Exception e){}
        new Thread(()->fs.pwd()).start();      
    }
}
