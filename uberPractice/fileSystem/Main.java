package uberPractice.fileSystem;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        FileSystem fs=new FileSystem();
        fs.mkdir("/abc/cd");
        fs.cd("../..");
        System.out.println(fs.pwd());
        fs.cd("/...");
    }
}
