package day04;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public int part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        return countXMAS(lines, 1, -1)
                + countXMAS(lines, 1, 0)
                + countXMAS(lines, 1, 1)
                + countXMAS(lines, -1, -1)
                + countXMAS(lines, -1, 0)
                + countXMAS(lines, -1, 1)
                + countXMAS(lines, 0, -1)
                + countXMAS(lines, 0, 1);
    }

    private int countXMAS(List<String> lines, int dX, int dY) {
        int count = 0;
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (charAt(lines, x, y) == 'X'
                        && charAt(lines, x + dX, y + dY) == 'M'
                        && charAt(lines, x + 2 * dX, y + 2 * dY) == 'A'
                        && charAt(lines, x + 3 * dX, y + 3 * dY) == 'S') {
                    count++;
                }

            }

        }
        return count;
    }

    private char charAt(List<String> lines, int x, int y) {
        if (y < 0 || y >= lines.size()) {
            return ' ';
        }
        String line = lines.get(y);
        if (x < 0 || x >= line.length()) {
            return ' ';
        }
        return line.charAt(x);
    }

    public int part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        int count = 0;
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                String l1 = "" + charAt(lines, x-1, y-1) + charAt(lines, x, y) + charAt(lines, x+1, y+1);
                String l2 = "" + charAt(lines, x-1, y+1) + charAt(lines, x, y) + charAt(lines, x+1, y-1);

                if ((l1.equals("MAS") || l1.equals("SAM")) && (l2.equals("MAS") || l2.equals("SAM"))) {
                    count++;
                }
            }
        }

        return count;
    }
}
