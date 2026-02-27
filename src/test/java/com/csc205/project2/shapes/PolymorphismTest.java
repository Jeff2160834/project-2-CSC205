package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests that verify polymorphic behavior across the 3D shape hierarchy.
 *
 * <p>These tests exercise the {@link ThreeDimensionalShape} interface and ensure
 * concrete implementations (Sphere, Cube, Cylinder, RectangularPrism, Octahedron)
 * behave correctly when referenced via the interface type. The test confirms
 * that interface methods are available and that runtime types are the expected
 * concrete classes.
 */
public class PolymorphismTest {

    @Test
    void shapesPolymorphicBehavior() {
        // Purpose: create concrete shapes but reference them by the interface type
        ThreeDimensionalShape s1 = new Sphere("S", "Red", 1.0);
        ThreeDimensionalShape c1 = new Cube("C", "Blue", 2.0);
        ThreeDimensionalShape cyl = new Cylinder("CY", "Green", 1.0, 2.0);
        ThreeDimensionalShape rp = new RectangularPrism("R", "Grey", 1.0, 2.0, 3.0);
        ThreeDimensionalShape o = new Octahedron("O", "Black", 1.0);

        // ensure interface methods are available and return expected types/values
        assertTrue(s1.getVolume() > 0);
        assertTrue(c1.getSurfaceArea() > 0);
        assertTrue(cyl.getVolume() > 0);
        assertTrue(rp.getSurfaceArea() > 0);
        assertTrue(o.getVolume() > 0);

        // also verify that runtime types are the concrete classes
        assertEquals(Sphere.class, s1.getClass());
        assertEquals(Cube.class, c1.getClass());
        assertEquals(Cylinder.class, cyl.getClass());
        assertEquals(RectangularPrism.class, rp.getClass());
        assertEquals(Octahedron.class, o.getClass());
    }
}