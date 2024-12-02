package day02;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.abs;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        return processInput(filepath, debug, false);
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        return processInput(filepath, debug, true);
    }

    private static long processInput(String filepath, boolean debug, boolean useProblemDampener) {
        List<String> lines = Input.readLines(filepath);

        return lines.stream()
                .map(line -> line.split(" "))
                .map(levels -> {
                    int[] levelsInt = new int[levels.length];
                    for (int i = 0; i < levels.length; i++) {
                        levelsInt[i] = Integer.parseInt(levels[i]);
                    }
                    return levelsInt;
                })
                .map(levels -> isSafe(debug, levels, useProblemDampener))
                .filter(b -> b)
                .count();
    }

    private static Boolean isSafe(boolean debug, int[] levels, boolean useProblemDampener) {
        int directionIndicator = levels[1] - levels[0];
        boolean dampenerUsed = !useProblemDampener;
        boolean useDampener = false;

        int a = 0;
        for (int i = 0; i < levels.length - 1; i++) {
            if (useDampener && !dampenerUsed) {
                dampenerUsed = true;
                useDampener = false;
            } else {
                a = levels[i];
            }
            int b = levels[i + 1];

            if (b - a == 0) {
                if (debug) System.out.println(i + ": EQUAL");
                if (useProblemDampener && !dampenerUsed) {
                    useDampener = true;
                }
                else return Boolean.FALSE;
            }
            if ((b - a) * directionIndicator < 0) {
                if (debug) System.out.println(i + ": DIRECTION CHANGED");
                if (useProblemDampener && !dampenerUsed) {
                    useDampener = true;
                }
                else return Boolean.FALSE;
            }
            if (abs(b - a) > 3) {
                if (debug) System.out.println(i + ": TOO MUCH DIFFERENCE");
                if (useProblemDampener && !dampenerUsed) {
                    useDampener = true;
                }
                else return Boolean.FALSE;
            }
        }
        if (debug) System.out.println("VALID");
        return Boolean.TRUE;
    }
}
