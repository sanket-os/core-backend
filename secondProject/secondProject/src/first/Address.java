package first;

// Shallow copy example

//public class Address {
//
//    String city;
//
//    Address(String city) {
//        this.city = city;
//    }
//}
//
//class Student {
//    String name;
//    Address address;
//
//    Student(String name, Address address) {
//        this.name = name;
//        this.address = address;
//    }
// Copy Constructor (SHALLOW)
//    Student(Student other) {
//        this.name = other.name;
//        this.address = other.address;   // reference copy
//    }
//}

//  _________________________________________________________

//  Deep copy example

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }

//    copy constructor for address
    Address(Address other) {
        this.city = other.city;
    }
}

class Student {

    String name;
    Address address;

    Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

//    Deep Copy Constructor
    Student(Student other) {
        this.name = other.name;
        this.address = new Address(other.address); // deep copy
    }

}
