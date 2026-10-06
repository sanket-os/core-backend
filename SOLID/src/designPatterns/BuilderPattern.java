package designPatterns;

final class User {

    private final String name;
    private final String email;
    private final int age;
    private final String phone;
    private final String address;
    private final boolean newsletterSubscribed;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
        this.newsletterSubscribed = builder.newsletterSubscribed;
    }

    public static class Builder {

        private String name;
        private String email;
        private int age;
        private String phone;
        private String address;
        private boolean newsletterSubscribed;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
             return this;
        }

        public Builder newsletterSubscribed(boolean subscribed) {
            this.newsletterSubscribed = subscribed;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", newsletterSubscribed='" + newsletterSubscribed +
                '}';
    }

}

public class BuilderPattern {

    public static void main(String[] args) {

        User user = new User.Builder()
                .name("Siri")
                .email("siri@example.com")
                .age(23)
                .phone("9818389743")
                .address("Atlanta")
                .newsletterSubscribed(true)
                .build();

        System.out.println(user);

    }

}



// Builder Design Pattern
//  The Builder Design Pattern is a creational design pattern used to construct complex objects step-by-step,
//  especially when an object has many optional parameters.

//The main idea is:
//Separate the process of constructing an object from the final object itself.

//It is particularly useful when a constructor would otherwise become difficult to read because it has many parameters.


// Problems & issues caused by those problems -
//1. Hard to read.

//2. Easy to make silent mistakes. Same signatures having same no. of parameters and same data type can get jumbled up
//with each other and nobody will notice this wrong data until somebody come across it

//3. Fragile, breaks easily. New field addition in large codebases becomes painful and updating it everywhere with hundreds
//of files accessing that constructor is really painful. We have to find and fix every single place where it was called
// This breaks OCP(Open to extension, close to modification) principle


// The Missing-Value Problem
// Constructor has total of 8 values but some of them are missing and to compensate that we add many more constructors
// These many constructors do not solve the problem at all, it just makes everything even worse by making the same problems
// by clashing with one another because of same no. of parameters and data types. This is even more difficult to read

// The constructors below with same method signature will clash eventually, even with all the possible combinations
// Student(String name, double psp);
// Student(String universityName, double psp); // This looks identical to the line above to Java. Won't compile.
// Only the type and order of parameters matter for telling constructors apart, never the names chosen for them.

// Making the constructor for every combination from the one with all the parameters to just one parameter looks just
// like a telescope and its many layers that we use to zoom in or out for the observation

//The Combinatorial Explosion
//If a class has n fields → there can be up to 2^n possible combinations.
//Our Student class has 8 fields → that's up to 256 possible combinations!

//- Rule of thumb: never use telescoping constructors.

// Problem with using Map<String, Object>:
//- It does solve the "too many constructors" problem
//- But it completely removes any safety checking by the compiler. A
//  wrong key just silently does nothing. A wrong type crashes the
//  program later, while it's running, not earlier, while writing code.

// class Student {
//    Student(Map<String, Object> map) {
//        ...
//    }
//}
// map.put("psp", "hello") => ClassCastException at runtime
// map.put("nmae", "Naman") (notice the typo) => when the code looks for "name", it just will not find it, with no clue as to why.


// The Fix: Use a Plain Class Instead of a Map
// class Helper {
//    String name;
//    int age;
//    double psp;
//    String universityName;
//    String batch;
//    long id;
//    int gradYear;
//    String phoneNumber;
//}

// Helper helper = new Helper();
//helper.name = "Naman";
//helper.age = 21;
//helper.psp = 89.21;
//
//Student s = new Student(helper);   // the checking (validation) happens HERE

// class Student {
//    ...
//    Student(Helper helper) {
//        if (helper.gradYear > 2022) {
//            throw new IllegalArgumentException("Grad year cannot be greater than 2022");
//        }
//        this.gradYear = helper.gradYear;
//        this.name = helper.name;
//        // ...
//    }
//}

// Helper lets values be safely collected, with the compiler catching mistakes automatically, no wrong types,
// no missing checks slipping through. Student's constructor becomes the one and only checkpoint,
// there is no way to get a Student object without passing through it.

// This exact idea, a helper object that collects values, checks them, then builds the real object,
// is called the Builder Design Pattern. What was called Helper here is what everyone in the real world
// actually calls a Builder.

// On the class side, the same typo is caught immediately, right where it is typed, the code simply will not compile,
// with an error pointing directly at the mistake.

// Hidden Pitfalls
// Adding validation logic inside the helper/collector class itself, instead of at the point the real object is built.
// The helper's job is only to collect raw values; the checking belongs at the one true entry point into the real object.


// The Builder Pattern, First Working Version
// Following good coding habits, fields on this helper should be private (hidden from outside),
// with public getter and setter methods used to read or change them, instead of touching the fields directly.

// public class Builder {
//    private String name;
//    private int age;
//    private double psp;
//    private String universityName;
//    private String batch;
//    private long id;
//    private int gradYear;
//    private String phoneNumber;
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public int getAge() { return age; }
//    public void setAge(int age) { this.age = age; }
//
//    public int getGradYear() { return gradYear; }
//    public void setGradYear(int gradYear) { this.gradYear = gradYear; }
//
//    // ...same pattern of getter/setter for every remaining field
//}

// public class Student {
//    String name;
//    int age;
//    double psp;
//    String universityName;
//    String batch;
//    long id;
//    int gradYear;
//    String phoneNumber;
//
//    Student(Builder builder) {
//        if (builder.getGradYear() > 2022) {
//            throw new IllegalArgumentException("Grad year cannot be greater than 2022");
//        }
//        this.gradYear = builder.getGradYear();
//        this.age = builder.getAge();
//        this.name = builder.getName();
//        // ...
//    }
//}

//public class Client {
//    public static void main(String[] args) {
//        Builder builder = new Builder();
//        builder.setAge(21);
//        builder.setName("Naman");
//        builder.setGradYear(2023);   // this will trigger our validation error
//
//        Student st = new Student(builder);
//    }
//}


// Making the Builder Production-Ready
//It gets improved in four small steps, each fixing exactly one problem, building on the same running code.

// Step 1: "How would a new developer even know Builder exists?"
//The fix: add a method on Student itself that hands back a Builder to start with.
// This method must run before any Student object exists yet, so it needs to be static.

//public class Student {
//    ...
//    public static Builder getBuilder() {
//        return new Builder();
//    }
//}

//Now:
//Builder builder = Student.getBuilder();   // changed: Student itself gives us the Builder
//builder.setAge(21);
//builder.setName("Naman");
//builder.setGradYear(2023);
//
//Student st = new Student(builder);

// Step 2: "Who is actually doing the real work?"
//Looking at new Student(builder), who is really doing the checking and creating,
// Builder or Student's constructor? It is Student's constructor for now.

//The fix: give Builder a build() method that does the checking, and then creates and hands back the finished Student.

//public Student build() {
//    if (getGradYear() > 2022) {
//        throw new IllegalArgumentException("Grad year cannot be greater than 2022");
//    }
//    return new Student(this);
//}

//Builder builder = Student.getBuilder();
//builder.setAge(21);
//builder.setName("Naman");
//builder.setGradYear(2023);
//
//Student st = builder.build();   // changed: Builder itself now checks + creates the Student

// Step 3: "Can we make this one smooth line instead of many?"
//Instead of four separate lines, why not chain everything into one:

//Student st1 = Student.getBuilder()
//                      .setAge(21)
//                      .setName("Naman")
//                      .setGradYear(2021)
//                      .build();

// public Builder setAge(int age) {
//    this.age = age;
//    return this;      // returns the Builder itself
//}

// Simple rule for EVERY setter on Builder:
//1. Set the value like normal
//2. Return "this" (the Builder itself) instead of void

//This one small habit is what lets calls chain together like
//.setAge(21).setName("Naman")... This style has a name: "fluent"
//chaining.

// Step 4: "Can someone still cheat and skip the Builder entirely?"
//Even after all these improvements, Student(Builder builder) is still public.
// That means anyone could still write new Student(someBuilder) directly, completely skipping build(), and skipping validation along with it.

//The obvious fix is to make the constructor private. But Builder's build() method needs to call this now-private constructor,
// and Builder currently lives outside Student, as its own separate class. In Java, only code written inside the same class is allowed
// to touch something private. So Builder needs to physically move inside Student, written as a class-inside-a-class, called a nested (inner) class.

// public class Student {
//    String name;
//    int age;
//    double psp;
//    String universityName;
//    String batch;
//    long id;
//    int gradYear;
//    String phoneNumber;
//
//    // fix from Step 1: Student tells you how to start
//    public static Builder getBuilder() {
//        return new Builder();
//    }
//
//    // fix from Step 4: constructor is now private
//    private Student(Builder builder) {
//        this.gradYear = builder.getGradYear();
//        this.age = builder.getAge();
//        this.name = builder.getName();
//        // ...
//    }
//
//    // fix from Step 4: Builder now lives INSIDE Student
//    static class Builder {
//        private String name;
//        private int age;
//        private int gradYear;
//        // ...remaining fields
//
//        // fix from Step 3: every setter returns "this"
//        public Builder setName(String name) { this.name = name; return this; }
//        public Builder setAge(int age) { this.age = age; return this; }
//        public Builder setGradYear(int gradYear) { this.gradYear = gradYear; return this; }
//
//        public int getGradYear() { return gradYear; }
//        public int getAge() { return age; }
//        public String getName() { return name; }
//
//        // fix from Step 2: Builder does the real building
//        public Student build() {
//            if (getGradYear() > 2022) {
//                throw new IllegalArgumentException("Grad year cannot be greater than 2022");
//            }
//            return new Student(this);
//        }
//    }
//}

// Immutable object
//      │
//      ├──► safer sharing
//      ├──► easier reasoning
//      ├──► easier concurrency
//      ├──► safer collection keys
//      ├──► safer caching
//      ├──► fewer accidental side effects
//      └──► easier-to-maintain code

// Recap of all 4 fixes:
//Step 1 → Student.getBuilder()      fixes: "how do I even start?"
//Step 2 → Builder.build()           fixes: "who does the real work?"
//Step 3 → every setter returns this fixes: "can I chain calls?"
//Step 4 → private constructor +     fixes: "can someone cheat and
//          Builder nested inside              skip validation?"
//          Student
//
//Final result:
//Student.getBuilder() → Builder (chainable setters)
//                     → .build() checks everything, THEN creates
//                       and returns the real Student



// AI Corner — The Bug in AI's "Clean" Builder

// The problem, explained simply:
//- The entire point of the Builder pattern is that once build()
//  finishes, we get a safe, checked, and STABLE object that can
//  never secretly change again.
//- Copying only the reference to a list (instead of copying the
//  actual values into a brand new list) breaks this promise.
//- The fix: always create a fresh, brand new copy of any list or map
//  inside the constructor, like new ArrayList<>(builder.phoneNumbers),
//  instead of just pointing to the original one.

// A good habit for using AI-generated code:
//1. Let AI write a first draft — it usually gets the overall shape
//   of a pattern right.
//2. Then always ask: "Is there anything here that can still be
//   changed from outside, after the object is supposedly finished?"
//
//This is the same kind of lesson as the missing `volatile` keyword
//in the Singleton class, AI is good at giving the general shape of a
//pattern, but catching the one or two small details that make the
//code actually safe and correct is still your job.

// Hidden Pitfalls
// Assuming any Builder that compiles cleanly is automatically safe.
//A missing defensive copy for a mutable field like a List or Map produces code that compiles fine, looks fine,
// and only reveals the bug when something external mutates the shared collection later, exactly like the volatile
// bug from the Singleton class in how invisible it is.

// Only checking primitive or immutable fields (like String, int) for correctness and skipping list/map fields.
// Primitive and String fields do not have this problem at all, since they cannot be mutated after being copied;
// the risk is specific to mutable, reference-type fields like collections.


// Where Builder Is Used in Real Software

// Firebase's RequestConfiguration.Builder (in the Android SDK) uses the exact same shape as the Student.Builder built in class:
//
//- A Builder class written inside the main class
//- Every setter method returns Builder (so you can chain calls)
//- A build() method that returns the final, checked object
//Google did not invent anything new here, they used exactly the same pattern built up from a simple problem in this class.
// What was called "best practice" today is literally the same code shape running inside real Android apps used by billions of people.

//- Firebase's RequestConfiguration.Builder
//- Java's own StringBuilder — every time you write
//  sb.append("a").append("b"), you've actually been using a Builder,
//  probably since your very first Java class, without realizing it!
//- OkHttp's Request.Builder — used in almost every Android/backend
//  app that makes internet requests
//- Lombok's @Builder annotation — auto-writes all the boilerplate
//  code written by hand in this class, for any class you want
//- AI/LLM API client libraries — request objects with model name,
//  system prompt, temperature, max_tokens, tools[], and more, all
//  optional settings
//- SQL query builders (like JPA Criteria API or jOOQ) — build a
//  database query piece by piece, instead of writing one giant,
//  hard-to-read query all at once

//A closer-to-home example: setting up a request to an AI/LLM API, with a system prompt, temperature, a maximum token limit,
// a list of tools, and various optional settings, is exactly the same "too many optional attributes, some needing checking" problem this class started with.


//Lombok's @Builder and @With Annotations
//Everything built by hand above, the nested static class, chainable setters,
// the checking build() method, is exactly the shape that a Java library called Lombok can
// generate automatically. Lombok is a library that plugs into the Java compiler and writes
// repetitive boilerplate code for you, based on small annotations (special @SomeWord markers) placed above a class.

//@Builder
//Adding @Builder above the Student class tells Lombok: "generate a nested Builder class for me,
// with a setter for every field that returns the builder itself, and a build() method that constructs the final object."


// import lombok.Builder;
//
//@Builder
//public class Student {
//    private String name;
//    private int age;
//    private double psp;
//    private int gradYear;
//    private List<String> phoneNumbers;
//}

//Using it looks exactly the same as below code:
//
//Student st = Student.builder()
//                     .name("Naman")
//                     .age(21)
//                     .gradYear(2021)
//                     .build();
//Note the method is called .builder() (lowercase, no "get") rather than .getBuilder(),
// this is simply Lombok's own naming convention, the idea underneath is identical.

// Hidden Pitfalls
//Assuming @Builder automatically adds validation. It only generates the structural boilerplate
// (nested class, chainable setters, build()); any actual checking rules, like the gradYear rule
// from this class, still need to be written by hand.

//Assuming @Builder automatically defensive-copies List or Map fields. Plain @Builder can carry the
// exact same shared-reference risk; Lombok's @Singular annotation is the
// specific tool that addresses this for collection fields.

//Confusing @Builder and @With as the same thing. @Builder is for constructing a new object the first time;
// @With is for producing a modified copy of an object that already exists, without changing the original.

// import lombok.With;
//import lombok.Value;
//
//@Value   // makes all fields private and final, and generates getters, no setters
//@With
//public class Student {
//    String name;
//    int age;
//    int gradYear;
//}

//Student original = new Student("Naman", 21, 2021);
//Student updated = original.withAge(22);   // a brand-new Student, with age changed to 22
//
// original.age is still 21 — completely unaffected


//Pros and cons
//Advantages
//1. Readability
//.age(29)
//.email("sam@example.com")
//is much clearer than:
//"sam@example.com", 29

//2. Handles optional parameters well
//You only specify what you need.
//new User.Builder()
//        .name("Sam")
//        .email("sam@example.com")
//        .build();

//3. Can create immutable objects
//The builder is mutable, but the final object can be immutable.
//Builder → mutable
//User    → immutable

//4. Centralized validation
//.build()
//can ensure that the resulting object is valid.

//5. Avoids constructor explosion
//You don't need dozens of overloaded constructors.

//Disadvantages
//1. More code
//For a simple class:
//record Point(int x, int y) {}
//is obviously better than creating a Point.Builder.

//2. More objects
//The builder itself is another object.

//3. Can be overengineering
//Don't use Builder simply because it is a design pattern.
//For:
//User user = new User("Sam", 29);

//a Builder probably doesn't add much value.


//Builder Pattern = construct a complex object step-by-step, especially when there are many
// optional/configurable properties, while keeping the final object clean and often immutable.

//___________________________________________________________________________________________________________________


// Common Pitfalls Summary
//
//1. Assuming the compiler catches a same-type parameter swap in a
//   constructor
//     -> Java only checks type and position, never parameter names,
//        so swapped Strings or ints compile fine and fail silently.
//
//2. Trying to solve "some fields might be missing" by adding more
//   and more constructors
//     -> spreads the same readability and mistake-proneness problems
//        across many places instead of fixing them; also runs into
//        Java's inability to distinguish same-type-and-order
//        constructors at all.
//
//3. Using Map<String, Object> to bundle many optional fields
//     -> removes all compile-time checking; wrong keys fail silently,
//        wrong types only fail later, at runtime.
//
//4. Adding validation logic inside the "helper"/collector class
//   itself, instead of at the point the real object gets built
//     -> the collector's job is only to gather raw values; checking
//        belongs at the one true entry point into the real object.
//
//5. Writing setters that return void instead of the Builder itself
//     -> breaks fluent chaining; .setAge(21).setName(...) cannot
//        compile if setAge() doesn't return something to call
//        .setName() on.
//
//6. Leaving the real object's constructor public after adding
//   getBuilder() and build()
//     -> anyone can still bypass the Builder and skip validation
//        entirely, until the constructor is made private.
//
//7. Copying a mutable field (List, Map) by reference instead of
//   making a defensive copy inside the real object's constructor
//     -> the "finished, checked, stable" object can still be changed
//        from outside later, through the original Builder or the
//        original collection reference.
//
//8. Assuming Lombok's @Builder automatically validates fields or
//   defensive-copies collections
//     -> it only generates the structural shape; validation logic
//        and collection safety (via @Singular) still need to be
//        added deliberately.



// Reaching for Builder on every class, regardless of size. For a tiny class like Point,
// with only x and y, a full Builder is unnecessary overhead; the pattern earns its extra boilerplate
// only once the number of fields, and how much checking they need, genuinely justifies it.


// Key Terminology
//Term	Meaning in Simple Words
//Telescoping constructors	A bad pattern where each constructor calls a slightly smaller one, adding one more
// field each time, resulting in many overlapping, hard-to-maintain constructors

//Builder	A separate helper object used to collect field values safely, check them, and then construct the real, validated object

//build()	The method on a Builder that performs final validation and returns the finished, real object

//Fluent chaining	A style where each setter method returns the same object it was called on (this), allowing calls to be linked together in one line

//Nested (inner) class	A class written entirely inside another class, allowed to access that outer class's private members, such as a private constructor

//Defensive copy	Creating a brand-new copy of a mutable field (like a List), instead of copying only the reference, so the
// original cannot secretly affect the new object later

//Lombok	A Java library that automatically generates repetitive boilerplate code (like Builder classes) based on annotations

//@Builder	A Lombok annotation that auto-generates a nested Builder class, chainable setters, and a build() method for a class

//@With	A Lombok annotation that generates methods returning a modified copy of an immutable object, leaving the original unchanged

//@Singular	A Lombok annotation used alongside @Builder for collection fields, that helps protect against th



// Frequently Asked Interview Questions
//1. Why is putting all fields into one large constructor considered bad practice for a class with many fields?
//It becomes hard to read (no labels on the values), easy to silently break (same-type parameters can be swapped without any
// compiler warning), and fragile (adding one new field requires updating every place in the codebase that calls the constructor).
//
//2. What are telescoping constructors, and why are they discouraged?
//A pattern where each constructor calls a slightly smaller constructor and adds one more field, meant to handle different
// combinations of missing fields. They are discouraged because they spread the same readability and mistake-proneness problems
// across many constructors instead of solving them, and Java's type-and-order-based overload rules mean not every combination can
// even be written without clashing.
//
//3. Why is using Map<String, Object> to collect a class's fields a worse idea than it first appears?
//It removes compile-time safety entirely. A wrong key (a typo) fails completely silently, with no field ever being set and no
// warning given. A wrong type only fails later, at runtime, with a ClassCastException, far from where the mistake was actually made.
//
//4. What is the core idea of the Builder design pattern?
//A separate helper object (the Builder) collects field values safely, using ordinary class fields and setters so the compiler can
// catch mistakes, and a build() method performs validation and constructs the real object only once everything checks out.
//
//5. Why must the real object's constructor be made private in a production-grade Builder?
//So that the only way to create the object is through Builder.build(), which always performs validation first. A public constructor
// would let anyone bypass the Builder entirely and create an unchecked, potentially invalid object directly.
//
//6. Why does the Builder class typically need to be nested inside the class it builds?
//Because the real object's constructor is private, and only code written inside the very same class can call a private constructor.
// If Builder were a separate, outside class, it would have no way to call that constructor at all.
//
//7. What must a setter method return for fluent chaining (.setX().setY()...) to work?
//It must return the Builder object itself (this), rather than void. Returning nothing would make it impossible to call another setter
// method directly on the result.
//
//8. What is a defensive copy, and why does a Builder need one for fields like List or Map?
//A defensive copy means creating a brand-new copy of a mutable field's contents, instead of just copying the reference to the original object.
// Without it, the original Builder's collection and the final object's collection point to the exact same memory, so changes made through the
// original Builder after build() has run can silently change the "already finished" object too.
//
//9. What does Lombok's @Builder annotation actually generate, and what does it not handle automatically?
//It generates the structural shape of the Builder pattern automatically: a nested Builder class, chainable setters, and a build() method.
// It does not automatically add custom validation rules, and its default handling of collection fields can have the same shared-reference
// risk discussed for mutable fields, unless @Singular is used.
//
//10. What is the difference between Lombok's @Builder and @With annotations?
//@Builder is used to construct a brand-new object safely, with validation, the first time. @With is used on an already-existing, immutable
// object to produce a new copy with one field changed, without ever modifying the original object.


// Self-Check Questions
//List the three problems, from Section 1, that appear when a class's fields are all crammed into one large constructor.
//Explain, in your own words, why Java cannot distinguish between Student(String name, double psp) and Student(String universityName, double psp) as two separate constructors.
//Why does a Map<String, Object> fail to catch a typo in a field's key, while a plain class with named fields does catch it?
//Write out, from memory, the four fixes applied to the basic Builder in Section 5, and what problem each one solves.
//Explain why a setter method on a Builder must return the Builder itself, rather than void, for chaining to work.
//Explain why the Builder class typically needs to be written as a nested class inside the object it builds, rather than as a fully separate class.
//Write a short code snippet showing how a shared (non-defensive-copy) List reference between a Builder and its built object can allow the built object's data to change after build() has already run.
//What is the fix for the problem in question 7, and why does it work?
//Name three real-world examples (from Section 7) where the Builder pattern is already used, without most developers realizing it.
//What is the key difference between what Lombok's @Builder annotation generates automatically, and what a developer still has to add by hand?