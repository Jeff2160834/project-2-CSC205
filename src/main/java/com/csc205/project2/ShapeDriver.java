package com.csc205.project2;

import com.csc205.project2.shapes.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ShapeDriver.java
 *
 * Demonstrates core Java OOP and array concepts using Shape3D subclasses:
 *   - Shape3D[] polymorphic array holding all shape references
 *   - Per-type and overall comparative analysis (largest volume & surface area)
 *   - Interactive console menu for custom shape creation with input validation
 *   - System.nanoTime() performance timing per shape for both calculations
 *   - Professional formatted output with ANSI colors and aligned columns
 *
 * Compile:
 *   javac ThreeDimensionalShape.java Shape3D.java Sphere.java Cube.java \
 *         Cylinder.java RectangularPrism.java ShapeDriver.java
 *
 * Run:
 *   java ShapeDriver
 *
 * @author  YourName
 * @version 3.0
 */
public class ShapeDriver {

    // =========================================================
    //  ANSI DISPLAY CONSTANTS
    // =========================================================
    private static final String RESET   = "\u001B[0m";
    private static final String BOLD    = "\u001B[1m";
    private static final String CYAN    = "\u001B[36m";
    private static final String GREEN   = "\u001B[32m";
    private static final String YELLOW  = "\u001B[33m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String RED     = "\u001B[31m";
    private static final String BLUE    = "\u001B[34m";
    private static final String WHITE   = "\u001B[37m";

    /** Width of every printed table row — keep consistent throughout. */
    private static final int TABLE_WIDTH = 78;

    /** Shared console scanner — closed only at program exit. */
    private static final Scanner scanner = new Scanner(System.in);

    // =========================================================
    //  POLYMORPHIC ARRAY  (Shape3D[] — the core data structure)
    // =========================================================
    /**
     * The single polymorphic array that holds every Shape3D reference,
     * regardless of concrete subtype.  Starts at capacity 8; resized
     * automatically with Arrays.copyOf whenever the user adds shapes.
     */
    private static Shape3D[] shapes     = new Shape3D[8];
    private static int       shapeCount = 0;   // actual number of shapes stored

    // =========================================================
    //  MAIN
    // =========================================================

    /**
     * Entry point — runs each program section in order.
     *
     * @param args unused command-line arguments
     */
    public static void main(String[] args) {

        printBanner();

        // 1. Populate the Shape3D[] array with one of each type
        loadDemoShapes();

        // 2. Show the full shape catalogue
        printSection("SECTION 1 — POLYMORPHIC SHAPE3D ARRAY");
        displayShapeArray();

        // 3. Compare shapes — overall winners + per-type winners
        printSection("SECTION 2 — COMPARATIVE ANALYSIS");
        runComparativeAnalysis();

        // 4. Benchmark each shape's calculation speed
        printSection("SECTION 3 — CALCULATION SPEED TIMING");
        runPerformanceTiming();

        // 5. Let the user add custom shapes interactively
        printSection("SECTION 4 — INTERACTIVE SHAPE CREATION");
        interactiveMenu();

        // 6. Re-display everything after user additions
        printSection("SECTION 5 — FINAL SESSION SUMMARY");
        displayShapeArray();
        runComparativeAnalysis();

        printFooter();
        scanner.close();
    }

    // =========================================================
    //  1 — LOAD DEMO SHAPES INTO THE ARRAY
    // =========================================================

    /**
     * Adds one pre-built instance of every concrete Shape3D subclass
     * into the polymorphic {@code shapes[]} array.
     * Demonstrates that a Shape3D reference can point to any subtype.
     */
    private static void loadDemoShapes() {
        // Each addShape() call stores a concrete object behind a Shape3D reference
        addShape(new Sphere          ("Alpha Sphere",  "Crimson",  7.50));
        addShape(new Sphere          ("Beta Sphere",   "Navy",     4.00));
        addShape(new Cube            ("Gamma Cube",    "Emerald",  6.00));
        addShape(new Cube            ("Delta Cube",    "Obsidian", 3.50));
        addShape(new Cylinder        ("Epsilon Cyl",   "Amber",    5.00, 10.0));
        addShape(new Cylinder        ("Zeta Cyl",      "Violet",   3.00,  8.0));
        addShape(new RectangularPrism("Eta Prism",     "Ivory",    8.00, 5.0, 4.0));
        addShape(new RectangularPrism("Theta Prism",   "Slate",    6.00, 6.0, 6.0));
    }

    /**
     * Appends a Shape3D reference to {@code shapes[]}, growing the array
     * by 50% capacity whenever it is full (mirrors ArrayList behaviour).
     *
     * @param shape any non-null Shape3D subclass instance
     */
    private static void addShape(Shape3D shape) {
        if (shapeCount == shapes.length) {
            // Resize: increase capacity by 50%
            shapes = Arrays.copyOf(shapes, shapes.length + shapes.length / 2);
        }
        shapes[shapeCount++] = shape;
    }

    // =========================================================
    //  2 — DISPLAY SHAPE ARRAY
    // =========================================================

    /**
     * Iterates {@code shapes[0..shapeCount-1]} polymorphically.
     * Every call to {@code getVolume()} / {@code getSurfaceArea()} is
     * dispatched at runtime to the correct subclass — polymorphism in action.
     */
    private static void displayShapeArray() {
        printTableHeader(
                String.format("  %-4s  %-20s  %-18s  %12s  %14s",
                        "#", "Name", "Type", "Volume", "Surface Area")
        );

        for (int i = 0; i < shapeCount; i++) {
            Shape3D s   = shapes[i];                        // Shape3D reference
            String type = s.getClass().getSimpleName();     // runtime type name

            System.out.printf(
                    shapeColor(type) + "  %-4d  %-20s  %-18s  %12.4f  %14.4f" + RESET + "%n",
                    i + 1,
                    s.getName(),
                    type,
                    s.getVolume(),         // polymorphic dispatch
                    s.getSurfaceArea()     // polymorphic dispatch
            );
        }
        printTableFooter();
        System.out.printf(WHITE + "  Array capacity: %d  |  Shapes stored: %d%n%n"
                + RESET, shapes.length, shapeCount);
    }

    // =========================================================
    //  3 — COMPARATIVE ANALYSIS
    // =========================================================

    /**
     * Finds the overall winner (largest volume + largest surface area)
     * across all shapes, then repeats the comparison per shape type,
     * showing constraint-based winners for each category.
     */
    private static void runComparativeAnalysis() {
        if (shapeCount == 0) {
            System.out.println(RED + "  No shapes to analyse." + RESET);
            return;
        }

        // ── 3a. Overall winners ───────────────────────────────────
        Shape3D overallVolWinner = findLargestByVolume(shapes, shapeCount);
        Shape3D overallSAWinner  = findLargestBySurface(shapes, shapeCount);
        Shape3D overallVolLoser  = findSmallestByVolume(shapes, shapeCount);
        Shape3D overallSALoser   = findSmallestBySurface(shapes, shapeCount);
        double  totalVolume      = sumVolumes(shapes, shapeCount);
        double  avgVolume        = totalVolume / shapeCount;
        double  totalSA          = sumSurfaceAreas(shapes, shapeCount);
        double  avgSA            = totalSA / shapeCount;

        // ── Display overall results ───────────────────────────────
        System.out.println(BOLD + "  OVERALL WINNERS  (all shapes combined)" + RESET);
        printDivider();
        printAnalysisRow("Largest Volume",        overallVolWinner, true);
        printAnalysisRow("Largest Surface Area",  overallSAWinner,  false);
        printAnalysisRow("Smallest Volume",        overallVolLoser,  true);
        printAnalysisRow("Smallest Surface Area",  overallSALoser,   false);
        System.out.printf(WHITE
                        + "  %-28s %12.4f units³%n"
                        + "  %-28s %12.4f units²%n"
                        + "  %-28s %12.4f units³%n"
                        + "  %-28s %12.4f units²%n"
                        + RESET,
                "Total Volume:",       totalVolume,
                "Total Surface Area:", totalSA,
                "Average Volume:",     avgVolume,
                "Average SA:",         avgSA
        );
        System.out.println();

        // ── 3b. Per-type constraint winners ──────────────────────
        System.out.println(BOLD + "  PER-TYPE CONSTRAINT WINNERS" + RESET);
        printDivider();

        String[] types = { "Sphere", "Cube", "Cylinder", "RectangularPrism" };

        for (String type : types) {
            // Collect all shapes of this type into a temporary sub-array
            Shape3D[] subset  = new Shape3D[shapeCount];
            int       subSize = 0;
            for (int i = 0; i < shapeCount; i++) {
                if (shapes[i].getClass().getSimpleName().equals(type)) {
                    subset[subSize++] = shapes[i];
                }
            }

            String color = shapeColor(type);
            if (subSize == 0) {
                System.out.printf(color + "  %-18s  (no shapes of this type present)%n%n"
                        + RESET, type);
                continue;
            }

            Shape3D typeVolWinner = findLargestByVolume(subset, subSize);
            Shape3D typeSAWinner  = findLargestBySurface(subset, subSize);

            System.out.println(color + BOLD + "  ► " + type + RESET);
            System.out.printf(color
                            + "    Largest Volume       → %-20s  %10.4f units³%n"
                            + "    Largest Surface Area → %-20s  %10.4f units²%n"
                            + RESET,
                    typeVolWinner.getName(), typeVolWinner.getVolume(),
                    typeSAWinner.getName(),  typeSAWinner.getSurfaceArea()
            );

            // Mini volume-comparison bar chart within the type group
            System.out.println(color + "    Volume comparison within type:" + RESET);
            double maxVol = typeVolWinner.getVolume();
            for (int i = 0; i < subSize; i++) {
                double pct = (subset[i].getVolume() / maxVol) * 100.0;
                int    bar = (int)(pct / 5);
                System.out.printf(color + "      %-20s [%-20s] %5.1f%%%n" + RESET,
                        subset[i].getName(),
                        "█".repeat(bar),
                        pct
                );
            }
            System.out.println();
        }
    }

    // ── Finder helpers (operate on any Shape3D[] sub-range) ─────

    /** @return shape with the maximum volume in arr[0..size-1] */
    private static Shape3D findLargestByVolume(Shape3D[] arr, int size) {
        Shape3D best = arr[0];
        for (int i = 1; i < size; i++)
            if (arr[i].getVolume() > best.getVolume()) best = arr[i];
        return best;
    }

    /** @return shape with the minimum volume in arr[0..size-1] */
    private static Shape3D findSmallestByVolume(Shape3D[] arr, int size) {
        Shape3D best = arr[0];
        for (int i = 1; i < size; i++)
            if (arr[i].getVolume() < best.getVolume()) best = arr[i];
        return best;
    }

    /** @return shape with the maximum surface area in arr[0..size-1] */
    private static Shape3D findLargestBySurface(Shape3D[] arr, int size) {
        Shape3D best = arr[0];
        for (int i = 1; i < size; i++)
            if (arr[i].getSurfaceArea() > best.getSurfaceArea()) best = arr[i];
        return best;
    }

    /** @return shape with the minimum surface area in arr[0..size-1] */
    private static Shape3D findSmallestBySurface(Shape3D[] arr, int size) {
        Shape3D best = arr[0];
        for (int i = 1; i < size; i++)
            if (arr[i].getSurfaceArea() < best.getSurfaceArea()) best = arr[i];
        return best;
    }

    /** @return total volume across arr[0..size-1] */
    private static double sumVolumes(Shape3D[] arr, int size) {
        double total = 0;
        for (int i = 0; i < size; i++) total += arr[i].getVolume();
        return total;
    }

    /** @return total surface area across arr[0..size-1] */
    private static double sumSurfaceAreas(Shape3D[] arr, int size) {
        double total = 0;
        for (int i = 0; i < size; i++) total += arr[i].getSurfaceArea();
        return total;
    }

    // =========================================================
    //  4 — PERFORMANCE TIMING
    // =========================================================

    /** Number of iterations used per shape in the benchmark. */
    private static final int TIMING_ITERATIONS = 1_000_000;

    /**
     * Benchmarks {@code getVolume()} and {@code getSurfaceArea()} for
     * every shape in the array using {@code System.nanoTime()}.
     * A warm-up pass is run first so the JIT compiler does not skew results.
     */
    private static void runPerformanceTiming() {
        System.out.printf(WHITE + "  Iterations per shape: %,d%n%n"
                + RESET, TIMING_ITERATIONS);

        printTableHeader(
                String.format("  %-4s  %-20s  %-18s  %13s  %13s",
                        "#", "Name", "Type", "Vol (ns/op)", "SA  (ns/op)")
        );

        long fastestVol = Long.MAX_VALUE;
        long fastestSA  = Long.MAX_VALUE;
        int  fastVolIdx = 0;
        int  fastSAIdx  = 0;

        for (int i = 0; i < shapeCount; i++) {
            Shape3D s = shapes[i];

            // Warm-up — prevents JIT from skewing the first real measurement
            for (int w = 0; w < 1_000; w++) { s.getVolume(); s.getSurfaceArea(); }

            // Volume timing
            long t0 = System.nanoTime();
            for (int j = 0; j < TIMING_ITERATIONS; j++) s.getVolume();
            long volNsPerOp = (System.nanoTime() - t0) / TIMING_ITERATIONS;

            // Surface area timing
            t0 = System.nanoTime();
            for (int j = 0; j < TIMING_ITERATIONS; j++) s.getSurfaceArea();
            long saNsPerOp = (System.nanoTime() - t0) / TIMING_ITERATIONS;

            if (volNsPerOp < fastestVol) { fastestVol = volNsPerOp; fastVolIdx = i; }
            if (saNsPerOp  < fastestSA)  { fastestSA  = saNsPerOp;  fastSAIdx  = i; }

            String color = shapeColor(s.getClass().getSimpleName());
            System.out.printf(color
                            + "  %-4d  %-20s  %-18s  %11d ns  %11d ns" + RESET + "%n",
                    i + 1, s.getName(), s.getClass().getSimpleName(),
                    volNsPerOp, saNsPerOp
            );
        }

        printTableFooter();
        System.out.printf(GREEN
                        + "  Fastest Volume Calc  → %-22s (%d ns/op)%n"
                        + "  Fastest SA Calc      → %-22s (%d ns/op)%n%n"
                        + RESET,
                shapes[fastVolIdx].getName(), fastestVol,
                shapes[fastSAIdx].getName(),  fastestSA
        );
    }

    // =========================================================
    //  5 — INTERACTIVE MENU
    // =========================================================

    /**
     * Console menu loop.  The user may create any shape type with custom
     * parameters, view the array, re-run analysis or timing, then exit.
     */
    private static void interactiveMenu() {
        boolean active = true;
        while (active) {
            System.out.println(CYAN + BOLD
                    + "  ┌──────────────────────────────────────────┐"
                    + "\n  │         INTERACTIVE SHAPE MENU           │"
                    + "\n  ├──────────────────────────────────────────┤"
                    + "\n  │  1 → Add a Sphere                        │"
                    + "\n  │  2 → Add a Cube                          │"
                    + "\n  │  3 → Add a Cylinder                      │"
                    + "\n  │  4 → Add a Rectangular Prism             │"
                    + "\n  │  5 → View current shape array            │"
                    + "\n  │  6 → Re-run comparative analysis         │"
                    + "\n  │  7 → Re-run performance timing           │"
                    + "\n  │  8 → Exit interactive mode               │"
                    + "\n  └──────────────────────────────────────────┘"
                    + RESET
            );
            System.out.print(YELLOW + "  Your choice: " + RESET);

            String choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1" -> { addShape(buildSphere());           confirmAdded(); }
                case "2" -> { addShape(buildCube());             confirmAdded(); }
                case "3" -> { addShape(buildCylinder());         confirmAdded(); }
                case "4" -> { addShape(buildRectangularPrism()); confirmAdded(); }
                case "5" -> displayShapeArray();
                case "6" -> runComparativeAnalysis();
                case "7" -> runPerformanceTiming();
                case "8" -> active = false;
                default  -> System.out.println(RED
                        + "  ✘  Please enter a number from 1 to 8.\n" + RESET);
            }
        }
    }

    /** Prints the name and metrics of the most recently added shape. */
    private static void confirmAdded() {
        Shape3D newest = shapes[shapeCount - 1];
        System.out.printf(GREEN
                        + "  ✔  %s \"%s\" added  |  Volume: %.4f  |  SA: %.4f  |  Array size: %d%n%n"
                        + RESET,
                newest.getClass().getSimpleName(),
                newest.getName(),
                newest.getVolume(),
                newest.getSurfaceArea(),
                shapeCount
        );
    }

    // ── Interactive shape builders ─────────────────────────────────

    /** Prompts for Sphere parameters and returns the new Sphere. */
    private static Sphere buildSphere() {
        printBuilderHeader("Sphere", "radius");
        String name  = promptString("  Name");
        String color = promptString("  Color");
        double r     = promptPositiveDouble("  Radius");
        return new Sphere(name, color, r);
    }

    /** Prompts for Cube parameters and returns the new Cube. */
    private static Cube buildCube() {
        printBuilderHeader("Cube", "side length");
        String name  = promptString("  Name");
        String color = promptString("  Color");
        double side  = promptPositiveDouble("  Side Length");
        return new Cube(name, color, side);
    }

    /** Prompts for Cylinder parameters and returns the new Cylinder. */
    private static Cylinder buildCylinder() {
        printBuilderHeader("Cylinder", "radius, height");
        String name   = promptString("  Name");
        String color  = promptString("  Color");
        double radius = promptPositiveDouble("  Radius");
        double height = promptPositiveDouble("  Height");
        return new Cylinder(name, color, radius, height);
    }

    /** Prompts for RectangularPrism parameters and returns the new prism. */
    private static RectangularPrism buildRectangularPrism() {
        printBuilderHeader("Rectangular Prism", "length, width, height");
        String name   = promptString("  Name");
        String color  = promptString("  Color");
        double length = promptPositiveDouble("  Length");
        double width  = promptPositiveDouble("  Width");
        double height = promptPositiveDouble("  Height");
        return new RectangularPrism(name, color, length, width, height);
    }

    // =========================================================
    //  INPUT VALIDATION HELPERS
    // =========================================================

    /**
     * Reads a non-empty trimmed string, retrying until valid input is given.
     *
     * @param prompt label displayed before the input cursor
     * @return validated non-empty string
     */
    private static String promptString(String prompt) {
        while (true) {
            System.out.print(WHITE + prompt + ": " + RESET);
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println(RED + "  ✘  Cannot be empty — please try again." + RESET);
        }
    }

    /**
     * Reads a positive {@code double}, retrying on non-numeric input
     * or values that are zero or negative.
     *
     * @param prompt label displayed before the input cursor
     * @return validated positive double
     */
    private static double promptPositiveDouble(String prompt) {
        while (true) {
            System.out.print(WHITE + prompt + " (> 0): " + RESET);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (val > 0) return val;
                System.out.println(RED + "  ✘  Must be greater than zero." + RESET);
            } catch (NumberFormatException ex) {
                System.out.println(RED + "  ✘  Not a valid number — please try again." + RESET);
            }
        }
    }

    // =========================================================
    //  FORMATTING HELPERS
    // =========================================================

    /**
     * Returns the ANSI colour for a given shape type name.
     *
     * @param typeName simple class name (e.g. "Sphere", "Cube")
     * @return ANSI escape colour string
     */
    private static String shapeColor(String typeName) {
        return switch (typeName) {
            case "Sphere"           -> MAGENTA;
            case "Cube"             -> BLUE;
            case "Cylinder"         -> GREEN;
            case "RectangularPrism" -> YELLOW;
            default                 -> WHITE;
        };
    }

    /**
     * Prints one row in the comparative analysis table.
     *
     * @param label      metric description (e.g. "Largest Volume")
     * @param shape      the winning shape
     * @param useVolume  true → show volume, false → show surface area
     */
    private static void printAnalysisRow(String label, Shape3D shape, boolean useVolume) {
        double value = useVolume ? shape.getVolume() : shape.getSurfaceArea();
        String unit  = useVolume ? "units³" : "units²";
        String color = shapeColor(shape.getClass().getSimpleName());
        System.out.printf(
                BOLD + "  %-28s" + RESET + color + " %-22s" + RESET
                        + WHITE + " %10.4f %s%n" + RESET,
                label + ":", shape.getName(), value, unit
        );
    }

    /** Prints a bold cyan header row followed by a divider. */
    private static void printTableHeader(String headerLine) {
        System.out.println(BOLD + CYAN + headerLine + RESET);
        printDivider();
    }

    /** Prints a divider then a blank line — used as a table footer. */
    private static void printTableFooter() {
        printDivider();
        System.out.println();
    }

    /** Prints a full-width horizontal divider line. */
    private static void printDivider() {
        System.out.println(CYAN + "  " + "─".repeat(TABLE_WIDTH) + RESET);
    }

    /** Prints a compact header labelling which shape is being built. */
    private static void printBuilderHeader(String type, String params) {
        System.out.println(GREEN + BOLD
                + "  ── New " + type + "  (parameters: " + params + ") ──" + RESET);
    }

    /** Prints a yellow double-rule section title. */
    private static void printSection(String title) {
        System.out.println(BOLD + YELLOW
                + "\n  ══════════════════════════════════════════════════════════════════"
                + "\n    " + title
                + "\n  ══════════════════════════════════════════════════════════════════"
                + RESET + "\n");
    }

    /** Prints the application title banner. */
    private static void printBanner() {
        System.out.println(CYAN + BOLD
                + "\n  ╔════════════════════════════════════════════════════════════════════╗"
                +   "\n  ║       3D SHAPE ANALYSIS SYSTEM  ·  ShapeDriver v3.0             ║"
                +   "\n  ║  Polymorphic Array · Constraint Analysis · Timing · User Input  ║"
                +   "\n  ╚════════════════════════════════════════════════════════════════════╝"
                + RESET + "\n");
    }

    /** Prints the session end footer. */
    private static void printFooter() {
        System.out.println(CYAN + BOLD
                + "\n  ╔════════════════════════════════════════════════════════════════════╗"
                +   "\n  ║              Session complete. Goodbye!                          ║"
                +   "\n  ╚════════════════════════════════════════════════════════════════════╝"
                + RESET + "\n");
    }
}