package com.csc205.project2.shapes;


/**
 * Abstract base class for three-dimensional shapes.
 *
 * <p>This class implements the {@link ThreeDimensionalShape} interface and
 * provides common properties and behavior shared across concrete 3D shapes.
 * Concrete subclasses must implement the protected abstract calculation
 * methods which are then exposed via the {@code getVolume()} and
 * {@code getSurfaceArea()} methods.
 *
 * <p>By centralizing name/color properties and a consistent {@code toString()},
 * subclasses gain a standard representation and contract for volume/surface
 * area calculations.
 *
 * <p>Input values for {@code name} and {@code color} are required and
 * validated in the constructor and setters (null not allowed).
 *
 * @author Auto-generated
 */
public abstract class Shape3D implements ThreeDimensionalShape {

    /** The shape's name (non-null). */
    private String name;

    /** The shape's color (non-null). */
    private String color;

    /**
     * Constructs a new Shape3D with the provided name and color.
     *
     * @param name  the name of the shape; must not be null
     * @param color the color of the shape; must not be null
     * @throws IllegalArgumentException if {@code name} or {@code color} is null
     */
    protected Shape3D(String name, String color) {
        setName(name);
        setColor(color);
    }

    /**
     * Returns the name of this shape.
     *
     * @return the non-null name string
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this shape.
     *
     * @param name the new name; must not be null
     * @throws IllegalArgumentException if {@code name} is null
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.name = name;
    }

    /**
     * Returns the color of this shape.
     *
     * @return the non-null color string
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of this shape.
     *
     * @param color the new color; must not be null
     * @throws IllegalArgumentException if {@code color} is null
     */
    public void setColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("color must not be null");
        }
        this.color = color;
    }

    /**
     * Returns the surface area of the shape.
     *
     * <p>This concrete implementation delegates to the protected abstract
     * {@link #calculateSurfaceArea()} method which concrete subclasses must
     * implement.
     *
     * @return the surface area (in square units)
     */
    @Override
    public double getSurfaceArea() {
        return calculateSurfaceArea();
    }

    /**
     * Returns the volume of the shape.
     *
     * <p>This concrete implementation delegates to the protected abstract
     * {@link #calculateVolume()} method which concrete subclasses must
     * implement.
     *
     * @return the volume (in cubic units)
     */
    @Override
    public double getVolume() {
        return calculateVolume();
    }

    /**
     * Calculates the surface area for the concrete shape implementation.
     *
     * <p>Subclasses must implement this method with the appropriate formula.
     * This method is protected because it's an implementation detail used by
     * the public {@link #getSurfaceArea()} method.
     *
     * @return the calculated surface area
     */
    protected abstract double calculateSurfaceArea();

    /**
     * Calculates the volume for the concrete shape implementation.
     *
     * <p>Subclasses must implement this method with the appropriate formula.
     * This method is protected because it's an implementation detail used by
     * the public {@link #getVolume()} method.
     *
     * @return the calculated volume
     */
    protected abstract double calculateVolume();

    /**
     * Returns a formatted string representation of the shape including its
     * type, name, color, surface area and volume. Surface area and volume are
     * formatted to two decimal places.
     *
     * @return a human-readable representation of this shape
     */
    @Override
    public String toString() {
        return String.format(
                "%s {name='%s', color='%s'}%n  - Surface Area: %.2f square units%n  - Volume: %.2f cubic units",
                this.getClass().getSimpleName(), name, color, getSurfaceArea(), getVolume());
    }
}