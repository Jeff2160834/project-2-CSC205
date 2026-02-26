package com.csc205.project2.shapes;

/**
 * A regular octahedron defined by its edge length.
 *
 * <p>Provides implementations of volume and surface area calculations for a
 * regular octahedron. Edge length must be positive.
 */
public class Octahedron extends Shape3D {

    /** Edge length of the octahedron (must be > 0). */
    private double edge;

    /**
     * Constructs an Octahedron with the given name, color and edge length.
     *
     * @param name  shape name
     * @param color shape color
     * @param edge  edge length in units; must be > 0
     * @throws IllegalArgumentException if edge <= 0 or name/color is null
     */
    public Octahedron(String name, String color, double edge) {
        super(name, color);
        setEdge(edge);
    }

    /**
     * Returns the edge length of the octahedron.
     *
     * @return edge length in units
     */
    public double getEdge() {
        return edge;
    }

    /**
     * Sets the edge length of the octahedron.
     *
     * @param edge edge length in units; must be > 0
     * @throws IllegalArgumentException if edge <= 0
     */
    public void setEdge(double edge) {
        if (!Double.isFinite(edge) || edge <= 0) {
            throw new IllegalArgumentException("edge must be > 0 and finite");
        }
        this.edge = edge;
    }

    @Override
    protected double calculateSurfaceArea() {
        // Surface area for a regular octahedron: 2 * sqrt(3) * a^2
        return 2.0 * Math.sqrt(3.0) * edge * edge;
    }

    @Override
    protected double calculateVolume() {
        // Volume for a regular octahedron: (sqrt(2) / 3) * a^3
        return (Math.sqrt(2.0) / 3.0) * edge * edge * edge;
    }

    /**
     * Returns an octahedron-specific string representation including edge,
     * surface area and volume (two decimal places).
     *
     * @return formatted string describing this octahedron
     */
    @Override
    public String toString() {
        return String.format(
                "Octahedron {name='%s', color='%s', edge=%.2f}%n  - Surface Area: %.2f square units%n  - Volume: %.2f cubic units",
                getName(), getColor(), edge, getSurfaceArea(), getVolume());
    }
}
