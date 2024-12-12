package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public static String readString(String filepath) {
        try {
            return Files.readString(Path.of("src/test/resources", filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readLines(String filepath) {
        try {
            return Files.readAllLines(Path.of("src/test/resources", filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] readIntLine(String filepath) {
        String line = readLines(filepath).get(0);
        String[] intTexts = line.split(",");
        int[] intArray = new int[intTexts.length];

        for (int i = 0; i < intArray.length; i++) intArray[i] = Integer.parseInt(intTexts[i]);

        return intArray;
    }

    public static int[][] readIntMap(String filepath) {
        List<String> lines = readLines(filepath);
        int[][] map = new int[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();

            for (int x = 0; x < chars.length; x++) {
                map[y][x] = Integer.parseInt(String.valueOf(chars[x]));
            }
        }
        return map;
    }

    public static List<XYValue<Character>> readXYValues(String filepath) {
        List<String> lines = readLines(filepath);

        List<XYValue<Character>> xyValues = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            char[] chars = lines.get(y).toCharArray();

            for (int x = 0; x < chars.length; x++) {
                xyValues.add(new XYValue<>(x, y, chars[x]));
            }
        }

        return xyValues;
    }

    public static List<XYValue<Character>> getXyValuesForBorder(List<String> lines) {
        List<XYValue<Character>> points = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            if (y == 0 || y == lines.size() - 1) {
                for (int x = 0; x < lines.get(y).length(); x++) {
                    points.add(new XYValue<>(x, y, lines.get(y).charAt(x)));
                }
            }
            else {
                points.add(new XYValue<>(0, y, lines.get(y).charAt(0)));
                points.add(new XYValue<>(lines.get(y).length() - 1, y, lines.get(y).charAt(lines.get(y).length() - 1)));
            }
        }
        return points;
    }
}
