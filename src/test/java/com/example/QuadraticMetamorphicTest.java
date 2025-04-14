package com.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class QuadraticMetamorphicTest {

    @Test
    public void testScalingInvariant() {
        double[][] testCases = {
            {1, -3, 2},
            {1, 2, 5},
            {0.5, -2, 1},
        };

        for (double[] testCase : testCases) {
            double a = testCase[0], b = testCase[1], c = testCase[2];
            double k = 3.0;

            try {
                String original = captureOutput(() -> {
                    try {
                        Quadratic.solveQuadratic(a, b, c);
                    } catch (NotEnoughPrecisionException e) {
                        throw new RuntimeException("Original input failed", e);
                    }
                });

                String scaled = captureOutput(() -> {
                    try {
                        Quadratic.solveQuadratic(a * k, b * k, c * k);
                    } catch (NotEnoughPrecisionException e) {
                        throw new RuntimeException("Scaled input failed", e);
                    }
                });

                assertEquals(normalize(original), normalize(scaled),
                        "Scaling inputs should yield same roots. Failed for: " + a + ", " + b + ", " + c);

            } catch (RuntimeException e) {
                fail("Exception thrown during quadratic solving: " + e.getMessage());
            }
        }
    }

    private String captureOutput(Runnable runnable) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        runnable.run();
        System.setOut(oldOut);
        return baos.toString().trim();
    }

    private String normalize(String s) {
        return s.replaceAll("x1=", "").replaceAll("x2=", "")
                .replaceAll("\\s+", "")
                .replaceAll(",", "")
                .replaceAll("([0-9]+\\.[0-9]{6})[0-9]*", "$1"); // truncate long decimals
    }
}
