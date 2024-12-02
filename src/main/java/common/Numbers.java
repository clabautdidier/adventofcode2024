package common;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Numbers {
    public static int distance(int num1, int num2) {
        return max(num1, num2) - min(num1, num2);
    }
}
