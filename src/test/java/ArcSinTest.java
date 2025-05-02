import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static arcsin.ArcSin.arcSin;
import static org.junit.jupiter.api.Assertions.*;

public class ArcSinTest {

    @Test
    void testInvalidIterations() {
        assertThrows(IllegalArgumentException.class, () -> {arcSin(1, -2);});
        assertThrows(IllegalArgumentException.class, () -> {arcSin(1, 0);});
    }

    @Test
    void testInvalidInputs() {
        //checking by eps to the side
        assertThrows(ArithmeticException.class, () -> {arcSin(-1 - Math.pow(10, -10), 10);});
        assertThrows(ArithmeticException.class, () -> {arcSin(1 + Math.pow(10, -10), 10);});
    }

    @ParameterizedTest
    @CsvSource({
            "-0.99999, -1.339",
            "-0.9, -1.106",
            "-0.8, -0.925",
            "-0.7, -0.775",
            "-0.6, -0.643",
            "-0.50001, -0.524"
    })
    void testSlowNegative(double input, double expected) {
        assertEquals(expected, arcSin(input, 11), 1e-3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.499999, -0.45, -0.4, -0.35, -0.3, -0.25, -0.2, -0.15, -0.1, -0.05, -0.000001})
    void testFastNegative(double value) {
        assertEquals(Math.asin(value), arcSin(value, 13), 1e-6);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.499999, 0.45, 0.4, 0.35, 0.3, 0.25, 0.2, 0.15, 0.1, 0.05, 0.000001})
    void testFastPositive(double value) {
        assertEquals(Math.asin(value), arcSin(value, 13), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({
            "0.99999, 1.339",
            "0.9, 1.106",
            "0.8, 0.925",
            "0.7, 0.775",
            "0.6, 0.643",
            "0.50001, 0.524"
    })
    void testSlowPositive(double input, double expected) {
        assertEquals(expected, arcSin(input, 11), 1e-3);
    }

    @Test
    void testArcSinNegativeOne() {
        assertEquals(-1.339, arcSin(-1, 11), 1e-3);
    }

    @Test
    void testArcSinPositiveOne() {
        assertEquals(1.339, arcSin(1, 11), 1e-3);
    }

    @Test
    void testArcSinZero() {
        assertEquals(0, arcSin(0, 11), 1e-3);
    }

    @Test
    void testPositivePointValueEdgeCase() {
        assertEquals(Math.asin(0.5), arcSin(0.5, 13), 1e-6);
    }

    @Test
    void testNegativePointValueEdgeCase() {
        assertEquals(Math.asin(-0.5), arcSin(-0.5, 13), 1e-6);
    }
}
