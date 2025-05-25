public class Queue {
    int maxCapacity;
    int[] queue;
    int front=0,last=0,currentCapacity=0;
    Queue(int capacity){
        this.maxCapacity=capacity;
        queue=new int[capacity];
    }
    public void add(int ele){
        if(currentCapacity==maxCapacity){
            System.out.println(" queue full");
        }
        currentCapacity++;
        queue[last]=ele;last=(last+1)%maxCapacity;
    }
    public int remove(){
        if(currentCapacity<=0)return -1;
        currentCapacity--;
        int frontEle=queue[front];
        front=(front+1)%maxCapacity;
        return frontEle;
    }

}

