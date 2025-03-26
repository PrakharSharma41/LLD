public class SingleTonTest {
    private static volatile SingleTonTest test=null;
    private SingleTonTest(){
    }
    public static SingleTonTest createSingleTonTestObject(){
        if(test==null){
            synchronized(SingleTonTest.class){
                test=new SingleTonTest();
            }
        }
        return test;
    }
}
