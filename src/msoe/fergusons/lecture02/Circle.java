package msoe.fergusons.lecture02;

public class Circle implements Shape {

    double radius;
    double centerX;
    double centerY;
    String color;

    /**
     * Default Constructor
     * @param color
     * @param radius
     * @param centerX
     * @param centerY
     */
    public Circle(String color, double radius, double centerX, double centerY) {
        this.color = color;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double perimeter() {
        return 2 * PI * radius;
    }

    public double area() {
        return PI * Math.pow(radius, 2);
    }

    public String getColor() {
        return color;
    }

    public double diameter() {
        return radius * 2;
    }
}
