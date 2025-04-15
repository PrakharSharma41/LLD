package hitCounter;

public class BetterApproach {
    volatile int[] hits=new int[300];
    volatile int[] timeStamp=new int[300];
    public void hit(Integer time){
        int idx=time%300;
        if(timeStamp[idx]!=time){
            hits[idx]=1;
            timeStamp[idx]=time;
        }else{
            hits[idx]++;
        }
    }
    public int getHits(int time) {
        int res = 0;
        for (int i = 0; i < 300; ++i) {
            if (time - timeStamp[i] < 300) {
                res += hits[i];
            }
        }
        return res;
    }    
}
