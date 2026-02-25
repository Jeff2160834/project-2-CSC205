package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cylinder} class.
 *
 * <p>Tests verify:
 * - constructor assigns name, color, radius and height
 * - getters and setters function and validate inputs
 * - surface area and volume formulas are implemented correctly
 * - boundary/edge cases for very small and very large numeric inputs
 */
public class CylinderTest {

    @Test
    void constructorAndGetters() {
        // Purpose: verify the constructor and geometry calculations
        Cylinder c = new Cylinder("Tube", "Yellow", 2.0, 3.0);
        assertEquals("Tube", c.getName());
        assertEquals("Yellow", c.getColor());
        assertEquals(2.0, c.getRadius(), 1e-9);
        assertEquals(3.0, c.getHeight(), 1e-9);

        double expectedSA = 2.0 * Math.PI * 2.0 * (2.0 + 3.0);
        double expectedV = Math.PI * 2.0 * 2.0 * 3.0;
        assertEquals(expectedSA, c.getSurfaceArea(), 1e-9);
        assertEquals(expectedV, c.getVolume(), 1e-9);
    }

    @Test
    void setterValidation() {
        // Purpose: confirm setters accept valid values and reject invalid ones
        Cylinder c = new Cylinder("C", "Blue", 1.0, 1.0);
        c.setRadius(0.25);
        c.setHeight(4.75);
        assertEquals(0.25, c.getRadius(), 1e-9);
        assertEquals(4.75, c.getHeight(), 1e-9);

        // invalid radius/height should throw
        assertThrows(IllegalArgumentException.class, () -> c.setRadius(0.0));
        assertThrows(IllegalArgumentException.class, () -> c.setRadius(-1.0));
        assertThrows(IllegalArgumentException.class, () -> c.setHeight(0.0));
        assertThrows(IllegalArgumentException.class, () -> c.setHeight(-1.0));
    }

    @Test
    void nullNameOrColor() {
        // Purpose: ensure Shape3D's non-null enforcement for name/color applies
        assertThrows(IllegalArgumentException.class, () -> new Cylinder(null, "Red", 1.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Cylinder("C", null, 1.0, 1.0));
    }

    @Test
    void boundaryVerySmallAndVeryLarge() {
        // Purpose: very small inputs produce tiny but finite volumes; very large may produce Infinity
        Cylinder c = new Cylinder("Tiny", "Black", Double.MIN_VALUE, Double.MIN_VALUE);
        double tinyVol = c.getVolume();
        // tiny values may underflow to 0; ensure non-negative and finite
        assertTrue(tinyVol >= 0);
        assertTrue(Double.isFinite(tinyVol));

        c.setRadius(1e154);
        c.setHeight(1e154);
        double vol = c.getVolume();
        assertFalse(Double.isNaN(vol));
        assertTrue(vol >= 0 || Double.isInfinite(vol));
    }
}
