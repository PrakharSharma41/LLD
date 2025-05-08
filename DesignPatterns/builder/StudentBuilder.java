package DesignPatterns.builder;

import java.util.List;

public abstract class StudentBuilder {
    int id;
    String name;
    int age;
    List<String>subjects;
    int rollNumber;
    public StudentBuilder setId(int id) {
        this.id = id;
        return this;
    }
    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    public StudentBuilder setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }
    public Student build(){
        return new Student(this);
    }
    abstract public StudentBuilder setSubjects();
}
