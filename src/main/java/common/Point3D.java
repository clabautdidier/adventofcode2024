package common;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point3D {
    private final int x;
    private final int y;
    private final int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "{" + "%5d".formatted(x) +
                "," + "%5d".formatted(y) +
                "," + "%5d".formatted(z) +
                '}';
    }

    public Point3D negate() {
        return new Point3D(-x, -y, -z);
    }

    public int getLineLength() {
        return (int) sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x == point3D.x && y == point3D.y && z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public Point3D rotatex(int times) {
        Point3D rotated = this;
        for (int i = 0; i < times; i++)
            rotated = new Point3D(rotated.x, rotated.z, -rotated.y);
        return rotated;
    }


    public Point3D rotatey(int times) {
        Point3D rotated = this;
        for (int i = 0; i < times; i++)
            rotated = new Point3D(-rotated.z, rotated.y, rotated.x);
        return rotated;
    }

    public Point3D rotateZ(int times) {
        Point3D rotated = this;
        for (int i = 0; i < times; i++)
            rotated = new Point3D(rotated.y, -rotated.x, rotated.z);
        return rotated;
    }

    public Point3D add(Point3D point3D) {
        return new Point3D(x + point3D.x, y + point3D.y, z + point3D.z);
    }

}
