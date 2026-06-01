package users;

public class User {

    public String name;
    private String password;
    // package private
    String email;

//    A constructor is a special method used to create and initialize an object.
//    It runs automatically when you use new.

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(User other) {
        this.name = other.name;
        this.email = other.email;
        this.password = other.password;
    }

    void print() {
        System.out.println(name);
        System.out.println(password);
        System.out.println(email);
    }

}
