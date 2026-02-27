package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link RectangularPrism} class.
 *
 * <p>These tests exercise:
 * - correct assignment of width, height and depth
 * - accuracy of surface area and volume formulas
 * - validation for zero/negative dimensions
 * - null checks for name and color
 * - handling of extreme numeric values
 */
public class RectangularPrismTest {

    @Test
    void constructorAndCalculations() {
        // Purpose: verify geometry calculations for a 2x3x4 prism
        RectangularPrism r = new RectangularPrism("Box", "Grey", 2.0, 3.0, 4.0);
        assertEquals("Box", r.getName());
        assertEquals("Grey", r.getColor());
        assertEquals(2.0, r.getWidth(), 1e-9);
        assertEquals(3.0, r.getHeight(), 1e-9);
        assertEquals(4.0, r.getDepth(), 1e-9);

        double expectedSA = 2.0 * (2.0 * 3.0 + 2.0 * 4.0 + 3.0 * 4.0);
        double expectedV = 2.0 * 3.0 * 4.0;
        assertEquals(expectedSA, r.getSurfaceArea(), 1e-9);
        assertEquals(expectedV, r.getVolume(), 1e-9);
    }

    @Test
    void setterValidation() {
        // Purpose: ensure setters accept valid values and reject invalid ones
        RectangularPrism r = new RectangularPrism("R", "Blue", 1.0, 1.0, 1.0);
        r.setWidth(5.0);
        r.setHeight(6.0);
        r.setDepth(7.0);
        assertEquals(5.0, r.getWidth(), 1e-9);
        assertEquals(6.0, r.getHeight(), 1e-9);
        assertEquals(7.0, r.getDepth(), 1e-9);

        assertThrows(IllegalArgumentException.class, () -> r.setWidth(0.0));
        assertThrows(IllegalArgumentException.class, () -> r.setWidth(-1.0));
        assertThrows(IllegalArgumentException.class, () -> r.setHeight(0.0));
        assertThrows(IllegalArgumentException.class, () -> r.setHeight(-1.0));
        assertThrows(IllegalArgumentException.class, () -> r.setDepth(0.0));
        assertThrows(IllegalArgumentException.class, () -> r.setDepth(-1.0));
    }

    @Test
    void nullNameOrColor() {
        // Purpose: Shape3D enforces non-null name and color
        assertThrows(IllegalArgumentException.class, () -> new RectangularPrism(null, "Red", 1.0, 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new RectangularPrism("R", null, 1.0, 1.0, 1.0));
    }

    @Test
    void boundaryVerySmallAndVeryLarge() {
        // Purpose: validate behavior on extreme numeric inputs
        RectangularPrism r = new RectangularPrism("Tiny", "Black", Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        double tinyVol = r.getVolume();
        // tiny values may underflow to 0; ensure non-negative and finite
        assertTrue(tinyVol >= 0);
        assertTrue(Double.isFinite(tinyVol));

        r.setWidth(1e154);
        r.setHeight(1e154);
        r.setDepth(1e154);
        double vol = r.getVolume();
        assertFalse(Double.isNaN(vol));
        assertTrue(vol >= 0 || Double.isInfinite(vol));
    }
}