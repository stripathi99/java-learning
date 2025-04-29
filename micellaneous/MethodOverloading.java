package micellaneous;

public class MethodOverloading {

    public static void main(String[] args) {
        method(0.0, 0);
    }

    private static void method(int a, double b) {
        System.out.println(a + " - " +b);
    }

    private static void method(double a, int b) {
        System.out.println(a + " - " +b);
    }

    private static void method(String a, String b) {
        System.out.println(a + " - " +b);
    }
}
