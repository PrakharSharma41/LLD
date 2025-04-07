package VotingAgency;

public class User {
    UserType userType;
    int id;
    public User(int id,UserType userType) {
        this.userType = userType;
        this.id=id;
    }
    public int getId() {
        return id;
    }
    public int getBathRoomTimeInMillis(){
        return (int)(Math.random()*5890+2000);
    }
    public UserType getUserType() {
        return userType;
    }
    @Override
    public String toString() {
        return "User [userType=" + userType + ", id=" + id + "]";
    }
}
