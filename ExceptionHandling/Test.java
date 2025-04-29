package ExceptionHandling;

public class Test {
    public static void main(String[] args) {
        Test t = new Test();
        try {
            System.out.println(t.divide(9, 7));
            System.out.println(t.divide(null, 0));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private int divide(Integer numerator, Integer denominator) throws IllegalArgumentException {
        if(numerator == null || denominator == null) throw new IllegalArgumentException("numerator and/or denominator cannot be null");
        if(denominator == 0) throw new NumberFormatException("Trying to divide by 0");
        return numerator / denominator;
    }
}
