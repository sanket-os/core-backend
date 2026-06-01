package users;

public class StudentHelper {

    public String getEmailOfUser(User user) {
        System.out.println(user.email);
        System.out.println(user.name);
        // System.out.println(user.password);
        return user.email;
    }

}
