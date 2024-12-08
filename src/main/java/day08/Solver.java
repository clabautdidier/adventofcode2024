package day08;

import common.Input;
import common.XYValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static common.Input.readXYValues;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        int sizeX = lines.get(0).length();
        int sizeY = lines.size();

        List<XYValue<Character>> antennas = Input.readXYValues(filepath).stream()
                .filter(xyValue -> xyValue.value != '.')
                .toList();

        Set<Point> antinodes = new HashSet<>();
        for (XYValue<Character> antenna : antennas) {
            for (XYValue<Character> otherAntenna : antennas) {
                if (antenna.equals(otherAntenna)) continue;

                if (otherAntenna.value == antenna.value) {
                    int dx = otherAntenna.x - antenna.x;
                    int dy = otherAntenna.y - antenna.y;

                    Point antinode = new Point(antenna.x + (dx * 2), antenna.y + (dy * 2));
                    if (antinode.x < 0 || antinode.x >= sizeX || antinode.y < 0 || antinode.y >= sizeY) continue;

                    antinodes.add(antinode);
                }
            }
        }

        return antinodes.size();
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        int sizeX = lines.get(0).length();
        int sizeY = lines.size();

        List<XYValue<Character>> antennas = Input.readXYValues(filepath).stream()
                .filter(xyValue -> xyValue.value != '.')
                .toList();

        Set<Point> antinodes = new HashSet<>();
        for (XYValue<Character> antenna : antennas) {
            for (XYValue<Character> otherAntenna : antennas) {
                if (antenna.equals(otherAntenna)) continue;

                if (otherAntenna.value == antenna.value) {
                    antinodes.add(new Point(antenna.x, antenna.y));

                    int dx = otherAntenna.x - antenna.x;
                    int dy = otherAntenna.y - antenna.y;

                    int i=2;
                    while (onGrid(antenna.x + (dx * i), antenna.y + (dy * i), sizeX, sizeY)) {
                        Point antinode = new Point(antenna.x + (dx * i), antenna.y + (dy * i));
                        antinodes.add(antinode);
                        i++;
                    }
                }
            }
        }

        return antinodes.size();
    }

    private boolean onGrid(int x, int y, int sizeX, int sizeY) {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }
}
