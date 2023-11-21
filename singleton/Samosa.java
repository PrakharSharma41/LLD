package singleton;

public class Samosa {
    // lazy way of creating singletom object(object is created only when it is required)
    private static Samosa samosa;
    private Samosa(){

    }   
    // public synchronized static Samosa getSamosa(){ // synchronized so that only one thread can access it any time
    //     if(samosa==null){
    //         samosa=new Samosa();
    //     }
    //     return samosa;
    // }
    public synchronized static Samosa getSamosa(){ 
        if(samosa==null){
            synchronized(Samosa.class){
                samosa=new Samosa();
            }
        }
        return samosa;
    }

}
