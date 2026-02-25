package com.csc205.project2;

import com.csc205.project2.shapes.Sphere;
import com.csc205.project2.shapes.Octahedron;

public class ShapeDriver {

    public static void main(String[] args) {
        // Create a Sphere and print its details
        Sphere s = new Sphere("UnitSphere", "Red", 1.0);
        System.out.println(s);

        // Create an Octahedron and print its details (smoke test)
        Octahedron o = new Octahedron("UnitOcta", "Blue", 3.0);
        System.out.println(o);
    }
}
