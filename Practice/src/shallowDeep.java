class Address {

    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        System.out.println(city);
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

class Person {

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

//    shallow
    public Person(Person other) {
        this.name = other.name;
        this.address = other.address;
    }

//    output
//LA
//LA
//New York
//New York


//    deep
//    public Person(Person other) {
//        this.name = other.name;
//        this.address = new Address(other.address.getCity());
//    }

//    output
//LA
//LA
//LA
//New York
//LA

}




public class shallowDeep {

    public static void main(String[] args) {

        Address address = new Address("LA");

        Person person1 = new Person("Sam", address);
        Person person2 = new Person(person1);

        person1.getAddress().getCity();
        person2.getAddress().getCity();

        person1.getAddress().setCity("New York");

        person1.getAddress().getCity();
        person2.getAddress().getCity();

    }

}


