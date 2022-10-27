package system_patterns;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Vector v1 = new Vector(1, 2);
        Vector v2 = new Vector(2, 3);
        Vector v3 = v1.add(v2);

        BigDecimal val = new BigDecimal(0);
        BigDecimal add = val.add(new BigDecimal(10));
    }
}
