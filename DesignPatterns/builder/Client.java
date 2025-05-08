package DesignPatterns.builder;

public class Client {
    public static void main(String[] args) {
        Director directorObj1 = new Director(new EngStudentBuilder());
        Student engineerStudent = directorObj1.createStudent();
        System.out.println(engineerStudent.toString());
    }
}
