// PROTOTYPE REGSISTRY PATTERN EXAMPLE 2

package designPatterns;

import java.util.ArrayList;
import java.util.List;


// ============================================================
// 1. PROTOTYPE INTERFACE
// ============================================================

//interface Prototype<T> {
//
//    T clone();
//}


// ============================================================
// 2. INTELLIGENT STUDENT
// ============================================================

class IntelligentStudent implements Prototype<IntelligentStudent> {

    private String name;
    private int age;
    private double intelligenceScore;

    // IMPORTANT:
    // List is mutable.
    //
    // If we simply copy the reference during cloning,
    // the original and cloned student will share the same List.
    private List<String> subjects;


    // ========================================================
    // DEFAULT CONSTRUCTOR
    // ========================================================

    public IntelligentStudent() {
        subjects = new ArrayList<>();
    }


    // ========================================================
    // COPY CONSTRUCTOR
    // ========================================================

    public IntelligentStudent(IntelligentStudent student) {

        // Immutable/simple fields
        this.name = student.name;
        this.age = student.age;
        this.intelligenceScore = student.intelligenceScore;


        // ----------------------------------------------------
        // IMPORTANT: DEEP COPY OF THE LIST
        // ----------------------------------------------------
        //
        // WRONG:
        //
        // this.subjects = student.subjects;
        //
        // This would make both students point to the SAME list.
        //
        // Instead:
        //
        // Create a new ArrayList containing the same elements.
        //
        this.subjects = new ArrayList<>(student.subjects);
    }


    // ========================================================
    // SETTERS
    // ========================================================

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIntelligenceScore(double intelligenceScore) {
        this.intelligenceScore = intelligenceScore;
    }


    // ========================================================
    // SUBJECT METHODS
    // ========================================================

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    public void removeSubject(String subject) {
        subjects.remove(subject);
    }


    // ========================================================
    // CLONE
    // ========================================================

    @Override
    public IntelligentStudent clone() {

        return new IntelligentStudent(this);
    }


    // ========================================================
    // toString()
    // ========================================================

    @Override
    public String toString() {

        return "IntelligentStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", intelligenceScore=" + intelligenceScore +
                ", subjects=" + subjects +
                '}';
    }
}


// ============================================================
// 3. STUDENT REGISTRY
// ============================================================

class StudentRegistry {

    private final java.util.Map<String, IntelligentStudent> students =
            new java.util.HashMap<>();


    // --------------------------------------------------------
    // Register a prototype
    // --------------------------------------------------------

    public void register(
            String key,
            IntelligentStudent student) {

        students.put(key, student);
    }


    // --------------------------------------------------------
    // Get a clone
    // --------------------------------------------------------

    public IntelligentStudent get(String key) {

        IntelligentStudent student = students.get(key);

        if (student == null) {
            throw new IllegalArgumentException(
                    "No student prototype found for: " + key
            );
        }

        // IMPORTANT:
        // Return a clone instead of the original prototype.
        return student.clone();
    }
}


// ============================================================
// 4. MAIN
// ============================================================

public class IntelligentStudentDemo {

    public static void main(String[] args) {

        // ====================================================
        // STEP 1: Create the registry
        // ====================================================

        StudentRegistry registry = new StudentRegistry();


        // ====================================================
        // STEP 2: Create an IntelligentStudent prototype
        // ====================================================

        IntelligentStudent intelligentStudent =
                new IntelligentStudent();

        intelligentStudent.setName("Alex");
        intelligentStudent.setAge(21);
        intelligentStudent.setIntelligenceScore(98.5);

        intelligentStudent.addSubject("Data Structures");
        intelligentStudent.addSubject("Algorithms");
        intelligentStudent.addSubject("Machine Learning");


        // ====================================================
        // STEP 3: Register the prototype
        // ====================================================

        registry.register(
                "top-student",
                intelligentStudent
        );


        // ====================================================
        // STEP 4: Create a student from the prototype
        // ====================================================

        IntelligentStudent student1 =
                registry.get("top-student");


        // Change clone-specific information
        student1.setName("Rahul");
        student1.setAge(23);

        student1.addSubject("Distributed Systems");


        // ====================================================
        // STEP 5: Create another student from same prototype
        // ====================================================

        IntelligentStudent student2 =
                registry.get("top-student");

        student2.setName("Priya");
        student2.setAge(22);

        student2.addSubject("Cloud Computing");


        // ====================================================
        // STEP 6: Print everything
        // ====================================================

        System.out.println("Original Prototype:");
        System.out.println(intelligentStudent);

        System.out.println();

        System.out.println("Student 1:");
        System.out.println(student1);

        System.out.println();

        System.out.println("Student 2:");
        System.out.println(student2);


        // ====================================================
        // STEP 7: Demonstrate independent mutable Lists
        // ====================================================

        System.out.println();
        System.out.println("Adding a subject only to Student 1...");

        student1.addSubject("Computer Networks");


        System.out.println();
        System.out.println("Student 1:");
        System.out.println(student1);

        System.out.println();

        System.out.println("Student 2:");
        System.out.println(student2);

        System.out.println();

        System.out.println("Original Prototype:");
        System.out.println(intelligentStudent);
    }
}


// OUTPUT
// Original Prototype:
//IntelligentStudent{name='Alex', age=21, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning]}
//
//Student 1:
//IntelligentStudent{name='Rahul', age=23, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning, Distributed Systems]}
//
//Student 2:
//IntelligentStudent{name='Priya', age=22, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning, Cloud Computing]}
//
//Adding a subject only to Student 1...
//
//Student 1:
//IntelligentStudent{name='Rahul', age=23, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning, Distributed Systems, Computer Networks]}
//
//Student 2:
//IntelligentStudent{name='Priya', age=22, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning, Cloud Computing]}
//
//Original Prototype:
//IntelligentStudent{name='Alex', age=21, intelligenceScore=98.5, subjects=[Data Structures, Algorithms, Machine Learning]}