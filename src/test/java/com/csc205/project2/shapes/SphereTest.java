package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Sphere} class.
 *
 * <p>These tests check:
 * - constructor and getter behavior (name, color, radius)
 * - correctness of the surface area and volume formulas
 * - validation of the radius setter (reject zero/negative)
 * - null checks for name and color inherited from Shape3D
 * - behavior for very small and very large radius values
 */
public class SphereTest {

    @Test
    void constructorAndGetters() {
        // Purpose: verify constructor assigns correct properties and math is accurate
        Sphere s = new Sphere("Ball", "Red", 1.0);
        assertEquals("Ball", s.getName());
        assertEquals("Red", s.getColor());
        assertEquals(1.0, s.getRadius(), 1e-9);

        // surface area = 4*pi*r^2 = 4*pi
        assertEquals(4.0 * Math.PI, s.getSurfaceArea(), 1e-9);
        // volume = 4/3*pi*r^3 = 4/3*pi
        assertEquals((4.0 / 3.0) * Math.PI, s.getVolume(), 1e-9);
    }

    @Test
    void setterValidation() {
        // Purpose: radius setter accepts valid values and rejects invalid ones
        Sphere s = new Sphere("S", "Blue", 2.0);
        s.setRadius(0.5);
        assertEquals(0.5, s.getRadius(), 1e-9);

        assertThrows(IllegalArgumentException.class, () -> s.setRadius(0.0));
        assertThrows(IllegalArgumentException.class, () -> s.setRadius(-2.0));
    }

    @Test
    void nullNameOrColor() {
        // Purpose: name and color must be non-null (enforced by Shape3D)
        assertThrows(IllegalArgumentException.class, () -> new Sphere(null, "Red", 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Sphere("S", null, 1.0));
    }

    @Test
    void boundaryVerySmallAndVeryLarge() {
        // Purpose: ensure tiny radii produce finite numeric results (may underflow to 0)
        Sphere s = new Sphere("Tiny", "Black", Double.MIN_VALUE);
        double tinyVol = s.getVolume();
        // volume may underflow to 0 for extremely small radii; ensure non-negative, finite and not NaN
        assertTrue(tinyVol >= 0);
        assertTrue(Double.isFinite(tinyVol));

        s.setRadius(1e154);
        double vol = s.getVolume();
        assertFalse(Double.isNaN(vol));
        assertTrue(vol >= 0 || Double.isInfinite(vol));
    }
}