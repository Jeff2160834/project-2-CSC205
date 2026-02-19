package com.csc205.project2.shapes;

/**
 * A rectangular prism defined by width, height, and depth.
 */
public class RectangularPrism extends Shape3D {

    private double width;
    private double height;
    private double depth;

    public RectangularPrism(String name, String color, double width, double height, double depth) {
        super(name, color);
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be > 0");
        }
        this.width = width;
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

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        if (depth <= 0) {
            throw new IllegalArgumentException("depth must be > 0");
        }
        this.depth = depth;
    }

    @Override
    protected double calculateSurfaceArea() {
        // 2*(wh + wd + hd)
        return 2.0 * (width * height + width * depth + height * depth);
    }

    @Override
    protected double calculateVolume() {
        return width * height * depth;
    }
}
