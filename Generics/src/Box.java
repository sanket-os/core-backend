    /*
     * ------------------------------------------------------------
     * Generic Class
     *
     * T represents "some type"
     * decided later by the compiler.
     * ------------------------------------------------------------
     */

public class Box<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

// Why were Generics introduced?
//Before Java 5, collections stored Object.
//Object item = list.get(0);

//Problems:
//Every retrieval required manual casting.
//Wrong casts caused ClassCastException at runtime.
//The compiler couldn't help detect mistakes.

//Generics solved this by allowing the compiler to know the intended type.
//List<String> names = new ArrayList<>();
//String name = names.get(0);
//No cast.
//Compile-time safety.

// What is <T>?
//T means
//"Some type that will be decided later."
//It is not Object.
//The compiler replaces it with the actual type when the class or method is used.