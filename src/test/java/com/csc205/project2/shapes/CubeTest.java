package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Cube} class.
 *
 * <p>These tests validate the core contract of the Cube class:
 * - constructor correctness (name/color/side)
 * - getter and setter behavior
 * - geometric calculation accuracy for surface area and volume
 * - validation and boundary checks for invalid and extreme inputs
 *
 * Each test method documents the intent and the expected behavior.
 */
public class CubeTest {

    @Test
    void constructorAndGetters() {
        // Purpose: verify constructor properly assigns name, color and side
        // and that the surface area and volume calculations match known values.
        Cube c = new Cube("MyCube", "Green", 2.0);
        assertEquals("MyCube", c.getName());
        assertEquals("Green", c.getColor());
        assertEquals(2.0, c.getSide(), 1e-9);

        // surface area = 6 * side^2 = 24
        assertEquals(24.0, c.getSurfaceArea(), 1e-9);
        // volume = side^3 = 8
        assertEquals(8.0, c.getVolume(), 1e-9);
    }

    @Test
    void setterValidation() {
        // Purpose: verify setter updates value and enforces validation rules
        Cube c = new Cube("C", "Blue", 1.0);
        c.setSide(3.5);
        assertEquals(3.5, c.getSide(), 1e-9);

        // invalid values (zero or negative) should throw
        assertThrows(IllegalArgumentException.class, () -> c.setSide(0.0));
        assertThrows(IllegalArgumentException.class, () -> c.setSide(-1.0));
    }

    @Test
    void nullNameOrColor() {
        // Purpose: Shape3D enforces non-null name and color; constructors should throw
        assertThrows(IllegalArgumentException.class, () -> new Cube(null, "Red", 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Cube("C", null, 1.0));
    }

    @Test
    void boundaryVerySmallAndVeryLarge() {
        // Purpose: verify extreme numeric inputs behave sensibly (tiny positive values,
        // and very large values that might overflow to Infinity but not NaN).
        Cube c = new Cube("Tiny", "Black", Double.MIN_VALUE);
        // tiny but may underflow to 0 -> ensure non-negative and finite
        double tinyVol = c.getVolume();
        assertTrue(tinyVol >= 0);
        assertTrue(Double.isFinite(tinyVol));

        // very large value may cause Infinity results; ensure not NaN
        c.setSide(1e308);
        double vol = c.getVolume();
        // volume may overflow to Infinity but should not be NaN
        assertFalse(Double.isNaN(vol));
        assertTrue(vol >= 0 || Double.isInfinite(vol));
    }
}