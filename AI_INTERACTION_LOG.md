/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: [Co-Pilot, GPT-5 mini]
* Generation Date: [2-18-26]
*
* Original Prompt:
* "["Create a Java abstract class called Shape3D that implements a ThreeDimensionalShape interface. The class should have private fields name (String) and color (String) with public getters and setters. Include input validation that throws IllegalArgumentException for null or empty values. Add a constructor that accepts both fields. 
* Provide concrete implementations of getVolume() and getSurfaceArea() that simply call their respective abstract methods. Override toString() 
* to return a formatted string showing the shape's name, color, volume, and surface area rounded to 2 decimal places. Include full JavaDoc documentation on all methods and the class itself."]"
*
* Follow-up Prompts (if any):
* 1. "[N/A]"
* 2. "[N/A]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*

/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: [Co-Pilot, GPT-5 mini]
* Generation Date: [2-18-26]
*
* Original Prompt:
* "[I would like to create Java Classes for each Shape for Sphere, Cube, and Cylinder that extend the Shape3D abstract class. Each class should have a constructor that accepts the necessary dimensions (e.g., radius for Sphere, side length for Cube, radius and height for Cylinder) and calls the superclass constructor with appropriate name and color values. Implement the getVolume() and getSurfaceArea() methods for each shape using the correct formulas. 
* Also include proper validation for the given constructors and be sure to utilize the given methods from the ThreeDimensionalShape interface. Ensure that all methods are properly documented with JavaDoc comments.]"
*
* Follow-up Prompts (if any):
* 1. "[Asked it to impprove the formatting of the toString() method to ensure that the volume and surface area are rounded to 2 decimal places.]"
* 2. "[Can we add better validation for finite numbers and numbers only > 0? Also can we create a new shape class, the shape will be an Octahedron and will follow the same format as the other shapes?]"
*
* Manual Modifications:
* - [N/A]
* - [N/A]
*
* Formula Verification:
* - Volume formula verified against: [I used Desmos to verify the formulas for each shape]
* - Surface area formula verified against: [I used Desmos to verify the formulas for each shape]
    */



/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: [Co-Pilot, GPT-5 mini]
* Generation Date: [2-23-26]
*
* Original Prompt:
* "["With each of the shape classes can we generate a Junit 5 test case. With each test I want to validate the constructors, getters and setters. Let's check the calculation accuracy of each of the shapes with 
* the volume and surface area methods. Set boundary testing check if there are any zero values or negative values or values that are too large or too small. Check any input values entered as a null or negative number.
* Check inheritance testing such as polymorphic behavior of the shape classes when referenced as a Shape3D type. Ensure that all test cases are properly documented with JavaDoc comments and follow best practices for unit testing in Java."]"
*
* Follow-up Prompts (if any):
* 1. "[I am running into AssertionFailedError with my shapes, what is occurring? I ended up posting the error and Co-Pilot implemented a fix.]"

*
* Manual Modifications:
* - [N/A]
* - [N/A]
*y
* Formula Verification:
* - Volume formula verified against: [I used Desmos to verify the formulas for each shape]
* - Surface area formula verified against: [I used Desmos to verify the formulas for each shape]
    */

/**
* AI GENERATION DOCUMENTATION
* ===========================
* AI Tool Used: [Claude AI and Sonnet 4.6]
* Generation Date: [2-23-26]
*
* Original Prompt:
* "[Update ShapeDriver.java to include the following enhancements: (1) Polymorphic Array — store all Shape3D subclass instances in a single Shape3D[] array; 
* (2) Comparative Analysis — iterate the array to find the shape with the largest volume AND largest surface area, displaying winners with their values clearly labeled; 
* (3) User Input — add an interactive console menu that lets the user create any shape type by entering custom parameters with input validation; 
* (4) Performance Timing — use System.nanoTime() to measure and display how long getVolume() and getSurfaceArea() take per shape in nanoseconds; 
* (5) Formatted Output — display all results in a clean, professional table with aligned columns, section headers, and clear labels for every metric."]"
*
* Follow-up Prompts (if any):
* 1. "[N/A]"
* 2. "[N/A]"
*
* Manual Modifications:
* - [List any changes you made to the AI output]
* - [Explain why changes were necessary]
*
* Formula Verification:
* - Volume formula verified against: [I used Desmos to verify the formulas for each shape]
* - Surface area formula verified against: [I used Desmos to verify the formulas for each shape]
    */