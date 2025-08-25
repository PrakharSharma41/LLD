package uberPractice.fileSystem;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        FileSystem fs=new FileSystem();
        fs.mkdir("/ab/cd");
        fs.cd("/a");
        System.out.println(fs.pwd());;
        fs.cd("cd");
    }
}
