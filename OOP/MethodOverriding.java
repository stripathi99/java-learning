package OOP;

public class MethodOverriding {
    static void method(Object obj) {
        System.out.println("Object");
    }

    static void method(String str) {
        System.out.println("String");
    }

    public static void main(String[] args) {
        method(null);
    }
}
