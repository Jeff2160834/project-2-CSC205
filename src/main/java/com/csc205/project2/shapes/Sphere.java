package com.csc205.project2.shapes;

/**
 * A sphere defined by a radius.
 *
 * <p>Provides implementations of volume and surface area calculations for a
 * sphere. Radius must be positive and finite.
 */
public class Sphere extends Shape3D {

    /** Radius of the sphere (must be > 0). */
    private double radius;

    /**
     * Constructs a Sphere with the given name, color and radius.
     *
     * @param name   shape name
     * @param color  shape color
     * @param radius radius in units; must be > 0 and finite
     * @throws IllegalArgumentException if radius &lt;= 0 or name/color is null
     */
    public Sphere(String name, String color, double radius) {
        super(name, color);
        setRadius(radius);
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return radius in units
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the sphere.
     *
     * @param radius radius in units; must be &gt; 0
     * @throws IllegalArgumentException if radius &lt;= 0
     */
    public void setRadius(double radius) {
        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException("radius must be > 0 and finite");
        }
        this.radius = radius;
    }

    @Override
    protected double calculateSurfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    protected double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    /**
     * Returns a sphere-specific string representation including radius,
     * surface area and volume (two decimal places).
     *
     * @return formatted string describing this sphere
     */
    @Override
    public String toString() {
        return String.format(
                "Sphere {name='%s', color='%s', radius=%.2f}%n  - Surface Area: %.2f square units%n  - Volume: %.2f cubic units",
                getName(), getColor(), radius, getSurfaceArea(), getVolume());
    }
}
