import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static arcsin.ArcSin.arcSin;
import static org.junit.jupiter.api.Assertions.*;

public class ArcSinTest {

    @Test
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {arcSin(1, -2);});
        assertThrows(IllegalArgumentException.class, () -> {arcSin(1, 0);});
        assertThrows(ArithmeticException.class, () -> {arcSin(-1 - Math.pow(10, -10), 10);});
        assertThrows(ArithmeticException.class, () -> {arcSin(1 + Math.pow(10, -10), 10);});
    }

    @Test
    void testKeyPoints() {
        assertEquals(-1.339, arcSin(-1, 11), 1e-3);
        assertEquals(0, arcSin(0, 11), 1e-3);
        assertEquals(1.339, arcSin(1, 11), 1e-3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.5, -0.4, -0.3, -0.2, -0.1, 0, 0.1, 0.2, 0.3, 0.4, 0.5})
    void testNormalPoints(double value) {
        assertEquals(Math.asin(value), arcSin(value, 13), 1e-6);
    }
}
