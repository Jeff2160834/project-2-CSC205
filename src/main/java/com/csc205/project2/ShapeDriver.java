package com.csc205.project2;

import com.csc205.project2.shapes.Sphere;

public class ShapeDriver {

    public static void main(String[] args) {
        // Create a Sphere and print its details
        Sphere s = new Sphere("UnitSphere", "Red", 1.0);
        System.out.println(s);
    }
}
