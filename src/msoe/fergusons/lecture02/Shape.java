package msoe.fergusons.lecture02;

/**
 * Shape interface that defines
 * common behavior for implementing classes.
 */
public interface Shape {

    /**
     * This defines a constant
     * (public static are implicit)
     */
    double PI = Math.PI;

    /**
     * Calculates the area of the shape
     * @return area of the shape
     */
    double area();

    /**
     * Calculates the perimeter of the shape
     * @return perimeter of the shape
     */
    double perimeter();

    /**
     * Returns the color of the shape
     * @return color as a string
     */
    String getColor();

    /**
     * Determines which shape has a larger area.
     * @param shape1 first shape
     * @param shape2 second shape
     * @return 0 if the areas are equal, 1 if shape1 is larger, and 2 if shape2 is larger.
     */
    static int maximumArea(Shape shape1, Shape shape2) {
        int returnValue = 0; // if shape areas are equal

        if(shape1.area() > shape2.area()) {
            returnValue = 1;
        } else if(shape1.area() < shape2.area()) {
            returnValue = 2;
        }

        return returnValue;
    }
}
