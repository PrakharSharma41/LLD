package DesignPatterns.iteratorDesign;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book>ls=new ArrayList<>();
        ls.iterator();
        Library lb=new Library(ls);
        Iterator itr=lb.createIterator();
        while(itr.hasNext()){
            itr.next();
        }
    }
}
