package DesignPatterns.singleton;
public class SingleTonTest {
    private static volatile SingleTonTest test=null;
    private SingleTonTest(){
    }
    public static SingleTonTest createSingleTonTestObject(){
        if(test==null){
            synchronized(SingleTonTest.class){
                if(test==null){
                    test=new SingleTonTest();
                }
            }
        }
        return test;
    }
}
