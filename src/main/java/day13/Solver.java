package day13;

import common.Input;
import common.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static java.lang.Math.floor;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        return processFile(filepath, 0);
    }

    public long part2(String filepath, boolean debug) throws IOException {
        return processFile(filepath, 10000000000000L);
    }

    private long processFile(String filepath, long add) {
        List<String> lines = Input.readLines(filepath);

        int lineNumber = 0;
        long sum = 0;

        while (lineNumber < lines.size()) {
            String lineA = lines.get(lineNumber);
            String lineB = lines.get(lineNumber+1);
            String linePrice = lines.get(lineNumber+2);

            sum += calculateCheapestPath(lineA, lineB, linePrice, add);

            lineNumber+=4;
        }
        return sum;
    }

    private long calculateCheapestPath(String lineA, String lineB, String linePrice, long add) {
        Tuple<Long, Long> moveA = parseXY(lineA);
        Tuple<Long, Long> moveB = parseXY(lineB);
        Tuple<Long, Long> price = parseXY(linePrice);
        price = new Tuple<>(price.x() + add, price.y() + add);

        double a = (double) (price.x() * moveB.y() - price.y() * moveB.x()) / (moveA.x()*moveB.y() - moveA.y()*moveB.x());
        double b = (double) (price.y() * moveA.x() - price.x() * moveA.y()) / (moveA.x()*moveB.y() - moveA.y()*moveB.x());

        if (a == floor(a) && b == floor(b)) {
            return (long) (a * 3 + b);
        }
        return 0L;
    }

    private Tuple<Long, Long> parseXY(String line) {
        int posX = line.indexOf('X');
        String[] parts = line.substring(posX+1).split(", ");
        return new Tuple<>(Long.parseLong(parts[0].substring(1)), Long.parseLong(parts[1].substring(2)));
    }

}
