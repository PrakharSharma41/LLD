import java.util.*;
public class main {
    static class Doctor{
        public Doctor(int x){
            this.x=x;
        }
        int x;
    }
    
    public static void main(String[] args) {
        // Calendar
        List<Doctor>ls=new ArrayList<>(Arrays.asList(new Doctor(2)));
        List<Doctor>d=ls.stream().filter(d1->d1.x>2).toList();
        System.out.println(d.size());
    }
}
