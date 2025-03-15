package amazonLocker;

public class Main {
    public static void main(String[] args) {
        LockerController lockerController=new LockerController();
        lockerController.createLocker(1);
        lockerController.createLocker(2);
        lockerController.createLocker(3);
        lockerController.createLocker(4);
        lockerController.createLocker(5);

        UserController userController=new UserController();
        User user1=userController.createUser(1);
        Locker locker=lockerController.bookLocker(user1);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        lockerController.takeParcelFromLocker(user1, locker, "12345");

    }
}
