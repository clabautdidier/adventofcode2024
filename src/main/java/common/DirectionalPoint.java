package common;

import java.awt.*;
import java.util.Objects;

public class DirectionalPoint extends Point {
    public char direction;

    public DirectionalPoint(int x, int y, char direction) {
        super(x, y);
        this.direction = direction;
    }

    public DirectionalPoint(Point point, char direction) {
        super(point);
        this.direction = direction;
    }

    public DirectionalPoint move() {
        if (direction == '<') {
            return new DirectionalPoint(x - 1, y, direction);
        } else if (direction == '^') {
            return new DirectionalPoint(x, y - 1, direction);
        } else if (direction == '>') {
            return new DirectionalPoint(x + 1, y, direction);
        } else if (direction == 'v') {
            return new DirectionalPoint(x, y + 1, direction);
        } else {
            throw new RuntimeException("Invalid direction: " + direction);
        }
    }

    public DirectionalPoint turnLeft() {
        if (direction == '<') {
            return new DirectionalPoint(x, y, 'v');
        } else if (direction == '^') {
            return new DirectionalPoint(x, y, '<');
        } else if (direction == '>') {
            return new DirectionalPoint(x, y, '^');
        } else if (direction == 'v') {
            return new DirectionalPoint(x, y, '>');
        } else {
            throw new RuntimeException("Invalid direction: " + direction);
        }
    }

    public DirectionalPoint turnRight() {
        if (direction == '<') {
            return new DirectionalPoint(x, y, '^');
        } else if (direction == '^') {
            return new DirectionalPoint(x, y, '>');
        } else if (direction == '>') {
            return new DirectionalPoint(x, y, 'v');
        } else if (direction == 'v') {
            return new DirectionalPoint(x, y, '<');
        } else {
            throw new RuntimeException("Invalid direction: " + direction);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DirectionalPoint that)) return false;
        if (!super.equals(o)) return false;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direction);
    }

    @Override
    public String toString() {
        return "DirectionalPoint{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
