package com.csc205.project2.shapes;

/**
 * A cube defined by the length of its side.
 */
public class Cube extends Shape3D {

    /** Length of the cube's side (must be > 0). */
    private double side;

    /**
     * Constructs a Cube with the given name, color and side length.
     *
     * @param name  shape name
     * @param color shape color
     * @param side  side length in units; must be > 0 and finite
     */
    public Cube(String name, String color, double side) {
        super(name, color);
        setSide(side);
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (!Double.isFinite(side) || side <= 0) {
            throw new IllegalArgumentException("side must be > 0 and finite");
        }
        this.side = side;
    }

    @Override
    protected double calculateSurfaceArea() {
        return 6.0 * side * side;
    }

    @Override
    protected double calculateVolume() {
        return side * side * side;
    }

    @Override
    public String toString() {
        return String.format(
                "Cube {name='%s', color='%s', side=%.2f}%n  - Surface Area: %.2f square units%n  - Volume: %.2f cubic units",
                getName(), getColor(), side, getSurfaceArea(), getVolume());
    }
}