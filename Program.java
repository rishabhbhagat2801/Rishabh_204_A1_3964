import java.util.Scanner;

enum triangle_type {
    Equilateral, Isosceles, Unknown
}

class Point {
    private double a, b;

    public Point(double a, double b) {
        this.a = a;
        this.a = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double distanceTo(Point other) {
        double da = a - other.getA();
        double db = b - other.getB();
        return Math.sqrt(da * da + db * db);
    }
}

class Triangle {
    private Point[] vertex;
    private triangle_type type;

    public Triangle(Point p1, Point p2, Point p3) {
        vertex = new Point[] { p1, p2, p3 };
        type = calculateType();
    }

    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < 3; i++) {
            int j = (i + 1) % 3;
            perimeter += vertex[i].distanceTo(vertex[j]);
        }
        return perimeter;
    }

    private triangle_type calculateType() {
        double side1 = vertex[0].distanceTo(vertex[1]);
        double side2 = vertex[1].distanceTo(vertex[2]);
        double side3 = vertex[2].distanceTo(vertex[0]);

        if (side1 == side2 && side2 == side3) {
            return triangle_type.Equilateral;
        } else if (side1 == side2 || side2 == side3 || side3 == side1) {
            return triangle_type.Isosceles;
        } else {
            return triangle_type.Unknown;
        }
    }

    public triangle_type getType() {
        return type;
    }

    public boolean containsPoint(Point p) {
        return false;
    }

    public double calculateArea() {
        return 0.0;
    }
}

class Geometry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTriangles = 3;

        Triangle[] triangles = new Triangle[numberOfTriangles];

        for (int i = 0; i < numberOfTriangles; i++) {
            System.out.println("Enter the coordinates of the vertices for Triangle " + (i + 1) + ":");
            Point p1 = new Point(scanner.nextDouble(), scanner.nextDouble());
            Point p2 = new Point(scanner.nextDouble(), scanner.nextDouble());
            Point p3 = new Point(scanner.nextDouble(), scanner.nextDouble());
            triangles[i] = new Triangle(p1, p2, p3);
        }

        for (int i = 0; i < numberOfTriangles; i++) {
            System.out.println(" Perimeter of : " + triangles[i].calculatePerimeter() + "Triangle " + (i + 1));
            System.out.println("Triangle " + (i + 1) + " Type: " + triangles[i].getType());
        }

        System.out.println("Enter the coordinates of a point to check whether if it's inside a triangle:");
        Point checkPoint = new Point(scanner.nextDouble(), scanner.nextDouble());

        for (int i = 0; i < numberOfTriangles; i++) {
            if (triangles[i].containsPoint(checkPoint)) {
                System.out.println("The point is inside the triangle " + (i + 1));
            } else {
                System.out.println("The point is not inside the triangle " + (i + 1));
            }
        }
        scanner.close();
    }
}
