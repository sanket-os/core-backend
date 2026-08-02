package labsession;

/*
        * Because Electronics inherits them from the Item class.
        *
        * This avoids duplicate code and follows one of the
        * fundamental principles of Object-Oriented Programming:
        *
        *              Code Reusability
        *
        * Electronics is therefore called a SUBCLASS (Child Class),
        * while Item is called the SUPERCLASS (Parent Class).
*/

public class Electronics extends Item{

    private int warranty;

    public Electronics(String id, String name, double price, int quantity, int warranty) {
          /* ----------------------------------------------------
                * super(...)
           * ----------------------------------------------------
           * super refers to the parent class.
           * Here we are calling the constructor of Item.
          */
        super(id, name, price, quantity);

        /*
         * After the Item constructor finishes,
         * we initialize the Electronics-specific field.
        */
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }


    /* Updates the warranty.
        * In production code, we would usually validate
        * that warranty is not negative.
    */

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
}

// Inheritance: Electronics reuses the common state and behavior from Item instead of duplicating code.

// Polymorphism with Generics: Because Electronics extends Item, it satisfies the bound T extends Item.
// This is exactly what allows your generic Inventory<T extends Item> to store Electronics, Book, Clothing,
// or any future subclass while still safely calling methods like getId(), getPrice(), and getQuantity().