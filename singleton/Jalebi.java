package singleton;

public class Jalebi {

    // eager way of creating singletom object
    private static Jalebi jalebi=new Jalebi();
    public static Jalebi getJalebi(){
        return jalebi;
    }
}
