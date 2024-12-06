package day06;

import common.DirectionalPoint;
import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public int part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        DirectionalPoint position = findGuard(lines);
        if (debug) LOG.info("Guard at: " + position);

        Set<Point> positions = getPath(debug, position, lines);

        assert positions != null;
        return positions.stream().map(Point::new).collect(Collectors.toSet()).size();
    }

    public int part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        DirectionalPoint position = findGuard(lines);
        if (debug) LOG.info("Guard at: " + position);

        Set<Point> positions = getPath(debug, position, lines);

        assert positions != null;

        positions.remove(position);
        return (int) positions.stream()
                .map(Point::new).collect(Collectors.toSet())
                .stream()
                .map(p -> addObstacle(lines, p))
                .map(l -> getPath(debug, position, l))
                .filter(Objects::isNull)
                .count();
    }

    private List<String> addObstacle(List<String> lines, Point p) {
        List<String> newLines = new ArrayList<>(lines);
        StringBuilder sb = new StringBuilder(newLines.get(p.y));
        sb.setCharAt(p.x, '#');
        newLines.set(p.y, sb.toString());
        return newLines;
    }

    private static Set<Point> getPath(boolean debug, DirectionalPoint position, List<String> lines) {
        Set<Point> positions = new HashSet<>();

        while (position.x >= 0 && position.x < lines.getFirst().length() && position.y >= 0 && position.y < lines.size()) {
            if (positions.contains(position)) {
                if (debug) drawMap(lines, positions);
                return null;
            }

            positions.add(position);

            if (position.direction == '<') {
                if (position.x > 0 && lines.get(position.y).charAt(position.x-1) == '#') {
                    position = position.turnRight();
                }
                else {
                    position = position.move();
                }
            } else if (position.direction == '^') {
                if (position.y > 0 && lines.get(position.y - 1).charAt(position.x) == '#') {
                    position = position.turnRight();
                }
                else {
                    position = position.move();
                }
            } else if (position.direction == '>') {
                if (position.x + 1 < lines.getFirst().length() && lines.get(position.y).charAt(position.x + 1) == '#') {
                    position = position.turnRight();
                }
                else {
                    position = position.move();
                }
            } else if (position.direction == 'v') {
                if (position.y + 1 < lines.size() && lines.get(position.y + 1).charAt(position.x) == '#') {
                    position = position.turnRight();
                }
                else {
                    position = position.move();
                }
            } else {
                throw new RuntimeException("Invalid direction: " + position.direction);
            }
            if (debug) System.out.println("Position: " + position);
        }
        return positions;
    }

    private static void drawMap(List<String> lines, Set<Point> positions) {
        System.out.println("----------------------------------------------");
        for (int y=0; y<lines.size(); y++) {
            StringBuilder sb = new StringBuilder(lines.get(y));
            for (int x=0; x<lines.get(y).length(); x++) {
                if (positions.contains(new Point(x, y))) {
                    sb.setCharAt(x, '*');
                }
            }
            System.out.println(sb);
        }
    }

    private DirectionalPoint findGuard(List<String> lines) {
        for (int y=0; y< lines.size(); y++) {
            String line = lines.get(y);

            int pos = line.indexOf('<');
            if (pos >= 0) {
                return new DirectionalPoint(pos, y, line.charAt(pos));
            }

            pos = line.indexOf('^');
            if (pos >= 0) {
                return new DirectionalPoint(pos, y, line.charAt(pos));
            }

            pos = line.indexOf('>');
            if (pos >= 0) {
                return new DirectionalPoint(pos, y, line.charAt(pos));
            }

            pos = line.indexOf('v');
            if (pos >= 0) {
                return new DirectionalPoint(pos, y, line.charAt(pos));
            }
        }
        throw new RuntimeException("Guard not found");
    }
}
