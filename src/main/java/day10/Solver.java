package day10;

import common.Input;
import common.XYValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static common.Input.getXyValuesForBorder;
import static common.Input.readXYValues;
import static java.util.Objects.nonNull;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        List<XYValue<Character>> points = readXYValues(filepath);

        return points.stream()
                .filter(point -> point.getValue() == '0')
                .mapToInt(point -> countPathsToANine(lines, new HashSet<>(), (int) point.getX(), (int) point.getY(), 0))
//                .peek(System.out::println)
                .sum();
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        List<XYValue<Character>> points = readXYValues(filepath);

        return points.stream()
                .filter(point -> point.getValue() == '0')
                .mapToInt(point -> countPathsToANine(lines, null, (int) point.getX(), (int) point.getY(), 0))
//                .peek(i -> System.out.println(i))
                .sum();
    }


    private int countPathsToANine(List<String> lines, HashSet<Point> points, int x, int y, int nextValue) {
        if (x < 0 || x >= lines.getFirst().length() || y < 0 || y >= lines.size()) return 0;

        int myValue = Integer.parseInt(lines.get(y).charAt(x) + "");
        if (myValue != nextValue) return 0;

        if (myValue == 9) {
            if (nonNull(points)) {
                if (points.contains(new Point(x, y))) {
                    return 0;
                }
                else
                    points.add(new Point(x, y));
            }
            return 1;
        }

        return countPathsToANine(lines, points, x + 1, y, nextValue + 1) +
                countPathsToANine(lines, points, x - 1, y, nextValue + 1) +
                countPathsToANine(lines, points, x, y + 1, nextValue + 1) +
                countPathsToANine(lines, points, x, y - 1, nextValue + 1);
    }
}
