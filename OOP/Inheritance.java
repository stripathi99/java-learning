package OOP;

public class Inheritance {
    public static void main(String[] args) {
        Base b = new Derived();
        System.out.println(b.returnX()); // prints x = 50
    }
}

class Base {
    int x = 10;

    public Base() {
        System.out.println("Base class constructor called.");
        incrementX();
    }

    public void incrementX() {
        System.out.println("Base class: incrementX() method called.");
        x += 10;
    }

    public int returnX() {
        return x;
    }
}

class Derived extends Base {
    public Derived() {
        System.out.println("Derived class constructor called.");
        incrementX();
    }

    public void incrementX() {
        System.out.println("Derived class: incrementX() method called.");
        x += 20;
    }
}