package builder;

public class Director {
    StudentBuilder builder;

    public Director(StudentBuilder builder) {
        this.builder = builder;
    }
    public Student createStudent(){
        if(builder instanceof EngStudentBuilder){
            return createEngineeringStudent();
        }
        return null;
    }
    public Student createEngineeringStudent(){
        return builder.setAge(12).setId(0).setSubjects().build();
    }
    
}
