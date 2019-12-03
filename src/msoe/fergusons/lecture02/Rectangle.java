package msoe.fergusons.lecture02;

public class Rectangle implements Shape {
    double height;
    double width;
    double upperLeftX;
    double upperLeftY;
    String color;

    public Rectangle(String color, double height, double width, double upperLeftX, double upperLeftY) {
        this.color = color;
        this.height = height;
        this.width = width;
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
    }

    public double area() {
        return height * width;
    }

    public double perimeter() {
        return (2 * height) + (2 * width);
    }

    public String getColor() {
        return color;
    }

    public boolean isSquare() {
        return (height == width);
    }
}
