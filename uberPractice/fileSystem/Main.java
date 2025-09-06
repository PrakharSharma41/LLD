package uberPractice.fileSystem;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        FileSystem fs=new FileSystem();
        new Thread(()->fs.mkdir("/abc/cd")).start();
        new Thread(()->fs.cd("./abc")).start();        
        new Thread(()->fs.cd("/")).start();
        // try{
        //     Thread.sleep(2000);
        // }catch(Exception e){}
        new Thread(()->System.out.println(fs.pwd())).start();        
    }
}
