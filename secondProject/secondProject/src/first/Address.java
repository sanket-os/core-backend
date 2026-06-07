package first;

// Shallow copy example

public class Address {

    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

public class Person {

    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

//    Copy Constructor (Shallow)
//    public Person(Person other) {
//
//        this.name = other.name;
//
//        // same Address object
//        this.address = other.address;
//    }

//    Deep Copy Constructor
    public Person(Person other) {

        this.name = other.name;

        this.address =
                new Address(
                        other.address.getCity()
                );
    }
}

//Address address = new Address("Mumbai");
//
//Person person1 =
//        new Person("Sam", address);
//
//Person person2 =
//        new Person(person1);

//person2.getAddress()
//       .setCity("Pune");
//
//System.out.println(
//        person1.getAddress().getCity()
//);
//
//System.out.println(
//        person2.getAddress().getCity()
//);

//Output:
//Pune
//Pune


//  _________________________________________________________

//  Deep copy example

//Address address =
//        new Address("Mumbai");
//
//Person person1 =
//        new Person("Sam", address);
//
//Person person2 =
//        new Person(person1);
//
//person2.getAddress().setCity("Pune");
//
//System.out.println(
//        person1.getAddress().getCity()
//);
//
//System.out.println(
//        person2.getAddress().getCity()
//);

//Output -
//Mumbai
//Pune

//  ------------------------------------------------------------------


//Important Interview Rule
//
//For collections:
//
//        new ArrayList<>(oldList)
//
//creates a new list,
//
//but the elements inside are not copied.
//
//        So if the elements are mutable objects:
//
//List<Person>
//
//this is still a shallow copy.
//
//        Example:
//
//List<Person> copy =
//        new ArrayList<>(original);
//
//The list is new.
//
//The Person objects are shared.


