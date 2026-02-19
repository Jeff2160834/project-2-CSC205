package com.csc205.project2.shapes;

/**
 * A right circular cylinder defined by radius and height.
 */
public class Cylinder extends Shape3D {

    private double radius;
    private double height;

    public Cylinder(String name, String color, double radius, double height) {
        super(name, color);
        setRadius(radius);
        setHeight(height);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be > 0");
        }
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("height must be > 0");
        }
        this.height = height;
    }

    @Override
    protected double calculateSurfaceArea() {
        // surface area = 2*pi*r*(r + h)
        return 2.0 * Math.PI * radius * (radius + height);
    }

    @Override
    protected double calculateVolume() {
        // volume = pi*r^2*h
        return Math.PI * radius * radius * height;
    }

    @Override
    public String toString() {
        return String.format(
                "Cylinder {name='%s', color='%s', radius=%.2f, height=%.2f}%n  - Surface Area: %.2f square units%n  - Volume: %.2f cubic units",
                getName(), getColor(), radius, height, getSurfaceArea(), getVolume());
    }
}
