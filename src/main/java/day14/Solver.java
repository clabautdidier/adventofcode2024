package day14;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug, int width, int height) throws IOException {
        List<String> lines = Input.readLines(filepath);
        List<Robot> robots = lines.stream().map(line -> new Robot(line, width, height)).toList();

        for (int i = 0; i < 100; i++) {
            for (Robot robot : robots) {
                robot.move();
            }
        }

        int quadrantBorderX = width /2;
        int quadrantBorderY = height /2;
        System.out.println("quadrantBorderX = " + quadrantBorderX);
        System.out.println("quadrantBorderY = " + quadrantBorderY);

        int[] robotCount = new int[4];
        for (Robot robot : robots) {
            if (robot.getPosition().x < quadrantBorderX && robot.getPosition().y < quadrantBorderY) {
                robotCount[0]++;
            } else if (robot.getPosition().x > quadrantBorderX && robot.getPosition().y < quadrantBorderY) {
                robotCount[1]++;
            } else if (robot.getPosition().x < quadrantBorderX && robot.getPosition().y > quadrantBorderY) {
                robotCount[2]++;
            } else if (robot.getPosition().x > quadrantBorderX && robot.getPosition().y > quadrantBorderY) {
                robotCount[3]++;
            }
        }

        return (long) robotCount[0] * robotCount[1] * robotCount[2] * robotCount[3];
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        List<Robot> robots = lines.stream().map(line -> new Robot(line, 101, 103)).toList();

        long seconds = 0;
        while (seconds < 101*103) {
            seconds++;
            for (Robot robot : robots) {
                robot.move();
            }

            if (!hasOverlappingRobots(robots)) {
                System.out.println("seconds = " + seconds);
                if (debug) drawMap(robots);

                return seconds;
            }
        }

        return 0;
    }

    private static void drawMap(List<Robot> robots) {
        for (int x=0; x<101; x++) {
            for (int y=0; y<103; y++) {
                int count = 0;

                for (Robot robot : robots) {
                    if (robot.getPosition().x == x && robot.getPosition().y == y) {
                        count++;
                    }
                }
                System.out.print(count);
            }
            System.out.println();
        }
    }

    private boolean hasOverlappingRobots(List<Robot> robots) {
        for (int x=0; x<101; x++) {
            for (int y=0; y<103; y++) {
                int count = 0;

                for (Robot robot : robots) {
                    if (robot.getPosition().x == x && robot.getPosition().y == y) {
                        count++;
                    }
                }
                if (count > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
