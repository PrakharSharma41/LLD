package splitwise.entity;

public class User {
    String userName;
    String userId;
    String contact;
    BalanceSheet sheet;
    public User(String userName, String contact,String userId) {
        this.userName = userName;
        this.contact = contact;
        this.userId=userId;
        sheet=new BalanceSheet(this);
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BalanceSheet getSheet() {
        return sheet;
    }
    public void setSheet(BalanceSheet sheet) {
        this.sheet = sheet;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (contact == null) {
            if (other.contact != null)
                return false;
        } else if (!contact.equals(other.contact))
            return false;
        return true;
    }
    public String getName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    

}
