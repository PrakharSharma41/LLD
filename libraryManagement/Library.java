import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Library {
    Catalog bookManager;
    Map<String,Member>members; // this can be removed, consider only one member in interview
    Map<Integer,Loan>loans;
    SearchBook searchBook;
    public Library() {
        members=new HashMap<>();
        bookManager=new Catalog();
        searchBook=new SearchBook();
        loans=new HashMap<>();
    }
    public void registerMember(String memberName){
        Member member=new Member(memberName, 0, memberName);
        members.putIfAbsent(memberName, member);
    }
    public void addBook(int id,String title,String author,int numberOfBooks){
        bookManager.addBook(id, title, author, numberOfBooks);
    }    
    public int borrowBook(String memberName,String author){
        Book book=bookManager.getBookByAuthor(author);
        if(!bookManager.isBookAvailable(book.getId())){
            System.out.println("book not available");
            return -1;
        }
        bookManager.changeBookCount(book.getId(), -1);
        Member member=members.get(memberName);
        Loan loan=new Loan( book); // ideally loan should not be created but for simplicity
        member.addLoan(loan);
        loans.put(loan.getId(), loan);
        System.out.println("book borrowed "+book);
        return loan.getId();
    }
    public int borrowBook1(String memberName, BookSearchStrategy strategy, String input) {
        Book book = strategy.search(input, bookManager);
        if (book == null || !bookManager.isBookAvailable(book.getId())) {
            System.out.println("book not available");
            return -1;
        }
    
        bookManager.changeBookCount(book.getId(), -1);
        Member member = members.get(memberName);
        Loan loan = new Loan( book);
        member.addLoan(loan);
        loans.put(loan.getId(), loan);
        System.out.println("book borrowed: " + book);
        return loan.getId();
    }

    public boolean returnBook(String  memberName,int loanId){
        Loan loan=loans.get(loanId);
        if(loan==null){
            System.out.println("loan not found");
            return false;
        }
        bookManager.changeBookCount(loan.getBook().getId(), 1);
        // Member member=members.get(memberName);
        loan.setReturnDate(LocalDate.now());
        System.out.println("returned");
        return true;
    }
    
}
