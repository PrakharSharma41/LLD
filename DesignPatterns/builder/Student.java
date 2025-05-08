package DesignPatterns.builder;

import java.util.List;

public class Student {
    int id;
    String name;
    int age;
    List<String>subjects;
    int rollNumber;
    public Student(StudentBuilder builder){ // all mandatory and optional fields of student is filled from builder object
        this.id=builder.id;
        this.name=builder.name;
        this.age=builder.age;
        this.subjects=builder.subjects;
        this.rollNumber=builder.rollNumber;
    }
}
