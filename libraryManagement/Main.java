public class Main {
    public static void main(String[] args) {
        Library library=new Library();
        library.registerMember("u1");
        library.addBook(0, "ghost", "author", 1);
        int loanId1=library.borrowBook("u1", "author");
        System.out.println(loanId1);
        // library.returnBook("u1", loanId1);
        int loanId2=library.borrowBook1("u1", new AuthorSearchStrategy(), "author");
        System.out.println(loanId2);
        // library.returnBook("u1", 0);
    }
}
