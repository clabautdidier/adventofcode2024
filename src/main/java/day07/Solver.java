package day07;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        return lines.stream()
                .map(line -> new Equation(line, "+*"))
                .filter(e -> !e.findOperators().isEmpty())
                .mapToLong(Equation::getTestValue)
                .sum();
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        return lines.stream()
                .map(line -> new Equation(line, "+*|"))
                .filter(e -> !e.findOperators().isEmpty())
                .mapToLong(Equation::getTestValue)
                .sum();
    }
}
