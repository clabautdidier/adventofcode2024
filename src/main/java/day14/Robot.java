package day14;

import java.awt.*;

public class Robot {
    private final int width;
    private final int height;

    private Point position;
    private Point velocity;

    public Robot(String line, int width, int height) {
        this.width = width;
        this.height = height;
        String parts[] = line.split(" ");
        String positionParts[] = parts[0].split("=");
        this.position = parsePosition(positionParts[1]);
        String velocityParts[] = parts[1].split("=");
        this.velocity = parsePosition(velocityParts[1]);
    }

    private static Point parsePosition(String positionPart) {
        String[] split = positionPart.split(",");
        return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public void move() {
        position.x += velocity.x;
        position.y += velocity.y;
        if (position.x < 0) {
            position.x = width + position.x;
        }
        if (position.x >= width) {
            position.x = position.x - width;
        }
        if (position.y >= height) {
            position.y = position.y - height;
        }
        if (position.y < 0) {
            position.y = height + position.y;
        }
    }

    public Point getPosition() {
        return position;
    }
}
