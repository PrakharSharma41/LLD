package VotingAgency;

public class Main {
    public static void main(String[] args) {
        BathRoom bathRoom=new BathRoom(3);
        // BathRoomController1 bathRoomController=new BathRoomController1(bathRoom);
        BathRoomController2 bathRoomController=new BathRoomController2(bathRoom,5);

        bathRoomController.addUser(new User(145, UserType.DEMOCRAT));
        bathRoomController.addUser(new User(245, UserType.DEMOCRAT));
        bathRoomController.addUser(new User(20, UserType.REPUBLICAN));
        bathRoomController.addUser(new User(1245, UserType.DEMOCRAT));
        bathRoomController.addUser(new User(10, UserType.REPUBLICAN));
        bathRoomController.addUser(new User(30, UserType.REPUBLICAN));
        bathRoomController.addUser(new User(40, UserType.REPUBLICAN));
        bathRoomController.start();
        System.out.println("main thread here");        
        // try{
        //     Thread.sleep(10000);
        //     bathRoomController.shutdown();
        // }catch(Exception e){}
    }
}
