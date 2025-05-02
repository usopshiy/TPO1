package arcsin;

public class ArcSin {

    public static int doubleFactorial(int x) {
        int sum = 1;
        for (int i = x; i > 0; i -= 2) {
            sum *= i;
        }
        return sum;
    }

    public static double arcSin(double x, int maxPower) {
        if (x < -1 || x > 1) {
            throw new ArithmeticException("arcSin: x must be between -1 and 1");
        }
        if (maxPower < 1) {
            throw new IllegalArgumentException("maxPower must be greater than 0");
        }
        double sum = x;
        for (int i = 3; i <= maxPower; i += 2) {
            sum += doubleFactorial(i - 2) * Math.pow(x, i) / (doubleFactorial(i - 1) * i);
        }
        return sum;
    }
}
