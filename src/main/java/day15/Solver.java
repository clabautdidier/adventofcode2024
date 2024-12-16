package day15;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        Map<Point, Character> map = null;
        StringBuilder movementBuilder = new StringBuilder();
        int mapSizeY = 0;
        boolean parsingMap = true;
        for (String line : lines) {
            if (line.isEmpty()) {
                parsingMap = false;
                map = Input.read2DMap(lines.subList(0, mapSizeY));
            }
            else {
                if (parsingMap) {
                    mapSizeY++;
                } else {
                    movementBuilder.append(line);
                }
            }
        }

        String movements = movementBuilder.toString();
        assert map != null;
        Point currentPos = map.entrySet().stream().filter(entry -> entry.getValue() == '@').findFirst()
                .map(Map.Entry::getKey).orElseThrow();
        map.put(currentPos, '.'); // delete @ character

        for (int i = 0; i < movements.length(); i++) {
            char movement = movements.charAt(i);
            currentPos = move(map, currentPos, movement);

            if (debug) printMap(mapSizeY, map, currentPos);
        }

        return map.entrySet().stream().filter(xyValue -> xyValue.getValue() == 'O')
                .map(Map.Entry::getKey)
                .mapToLong(point -> point.x + 100L * point.y)
                .sum();
    }

    private static void printMap(int mapSizeY, Map<Point, Character> map, Point currentPos) {
        for (int y = 0; y< mapSizeY; y++) {
            for (int x = 0; x< mapSizeY; x++) {
                if (currentPos.x == x && currentPos.y == y) {
                    System.out.print('@');
                }
                else {
                    System.out.print(map.get(new Point(x, y)));
                }
            }
            System.out.println();
        }
    }

    private Point move(Map<Point, Character> map, Point currentPos, char movement) {
        int directionX = 0;
        int directionY = 0;
        if (movement == '^') {
            directionY = -1;
        }
        else if (movement == 'v') {
            directionY = 1;
        }
        else if (movement == '<') {
            directionX = -1;
        }
        else if (movement == '>') {
            directionX = 1;
        }

        int count = 1;
        while (map.get(new Point(currentPos.x + (directionX * count), currentPos.y + (directionY * count))) == 'O') {
            count++;
        }

        char lastCharInDirection = map.get(new Point(currentPos.x + (directionX * count), currentPos.y + (directionY * count)));
        if (lastCharInDirection == '.') {
            while (count > 0) {
                map.put(new Point(currentPos.x + (directionX * count), currentPos.y + (directionY * count)),
                        map.get(new Point(currentPos.x + (directionX * (count - 1)), currentPos.y + (directionY * (count - 1)))));
                count--;
            }

            currentPos = new Point(currentPos);
            currentPos.translate(directionX, directionY);
        }

        return currentPos;
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        return 0;
    }
}
