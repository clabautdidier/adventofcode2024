package day01;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public int part1(String filepath, boolean debug) throws IOException {
        Lists lists = getLists(filepath, debug);

        int totalDistance = 0;
        for (int i = 0; i < lists.list1().length; i++) {
            totalDistance += Math.abs(lists.list1()[i] - lists.list2()[i]);
        }

        return totalDistance;
    }

    private static Lists getLists(String filepath, boolean debug) {
        List<String> lines = Input.readLines(filepath);
        int[] list1 = new int[lines.size()];
        int[] list2 = new int[lines.size()];

        int numberSize = lines.get(0).indexOf(' ');

        for (int i = 0; i < lines.size(); i++) {
            if (debug)
                System.out.println(lines.get(i) + " == " + lines.get(i).substring(0, numberSize) + " == " + lines.get(i).substring(numberSize + 3));
            list1[i] = Integer.parseInt(lines.get(i).substring(0, numberSize));
            list2[i] = Integer.parseInt(lines.get(i).substring(numberSize + 3));
        }

        Arrays.sort(list1);
        Arrays.sort(list2);
        Lists lists = new Lists(list1, list2);
        return lists;
    }

    private record Lists(int[] list1, int[] list2) {
    }

    public int part2(String filepath, boolean debug) throws IOException {
        Lists lists = getLists(filepath, debug);

        int totalCount = 0;
        for (int i = 0; i < lists.list1().length; i++) {
            int count = 0;

            for (int j = 0; j < lists.list2().length; j++) {
                if (lists.list1()[i] == lists.list2()[j]) {
                    count++;
                }
            }

            totalCount += count * lists.list1()[i];
        }

        return totalCount;
    }
}
