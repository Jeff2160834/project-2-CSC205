package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Octahedron} class.
 *
 * <p>Tests validate:
 * - constructor and property getters
 * - edge setter validation
 * - surface area and volume calculations using exact formulas
 * - null checks for name/color
 * - boundary behavior for tiny and very large edge lengths
 */
public class OctahedronTest {

    @Test
    void constructorAndCalculations() {
        // Purpose: confirm properties are set and formulas are correct for a=3.0
        Octahedron o = new Octahedron("Octa", "Cyan", 3.0);
        assertEquals("Octa", o.getName());
        assertEquals("Cyan", o.getColor());
        assertEquals(3.0, o.getEdge(), 1e-9);

        // Surface area = 2*sqrt(3)*a^2
        double expectedSA = 2.0 * Math.sqrt(3.0) * 9.0;
        double expectedV = (Math.sqrt(2.0) / 3.0) * 27.0; // a^3 = 27
        assertEquals(expectedSA, o.getSurfaceArea(), 1e-9);
        assertEquals(expectedV, o.getVolume(), 1e-9);
    }

    @Test
    void setterValidation() {
        // Purpose: edge setter should accept positive values and reject non-positive ones
        Octahedron o = new Octahedron("O", "Magenta", 1.0);
        o.setEdge(2.5);
        assertEquals(2.5, o.getEdge(), 1e-9);

        assertThrows(IllegalArgumentException.class, () -> o.setEdge(0.0));
        assertThrows(IllegalArgumentException.class, () -> o.setEdge(-1.0));
    }

    @Test
    void nullNameOrColor() {
        // Purpose: ensure Shape3D's non-null enforcement applies to Octahedron
        assertThrows(IllegalArgumentException.class, () -> new Octahedron(null, "Red", 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Octahedron("O", null, 1.0));
    }

    @Test
    void boundaryVerySmallAndVeryLarge() {
        // Purpose: check tiny positive edges and very large edges behavior
        Octahedron o = new Octahedron("Tiny", "Black", Double.MIN_VALUE);
        double tinyVol = o.getVolume();
        // tiny values may underflow to 0; ensure non-negative and finite
        assertTrue(tinyVol >= 0);
        assertTrue(Double.isFinite(tinyVol));

        o.setEdge(1e154);
        double vol = o.getVolume();
        assertFalse(Double.isNaN(vol));
        assertTrue(vol >= 0 || Double.isInfinite(vol));
    }
}