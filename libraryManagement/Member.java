import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
    String name;
    int memberId;
    String contact;
    List<Loan>loans;
    public Member(String name, int memberId, String contact) {
        this.name = name;
        this.memberId = memberId;
        this.contact = contact;
        loans=new ArrayList<>();
    }
    public void addLoan(Loan loan){
        loans.add(loan);
    }

}
