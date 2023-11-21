package singleton;

public class Example {
    public static void main(String[] args) {
        Samosa samosa1=Samosa.getSamosa();
        System.out.println(samosa1.hashCode());
        Samosa samosa2=Samosa.getSamosa();
        System.out.println(samosa2.hashCode());
        System.out.println(Jalebi.getJalebi().hashCode());
        System.out.println(Jalebi.getJalebi().hashCode());
    }    
}
