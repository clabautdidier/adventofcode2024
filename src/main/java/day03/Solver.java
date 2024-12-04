package day03;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        return lines.stream()
                .map(line -> performMuls(line, "mul\\(\\d*,\\d*\\)", debug))
                .reduce(0L, Long::sum);
    }

    private long performMuls(String line, String regex, boolean debug) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        long total = 0;

        while (matcher.find()) {
            String group = matcher.group();
            String[] nums = group.substring(4, group.length() - 1).split(",");
            long a = Long.parseLong(nums[0]);
            long b = Long.parseLong(nums[1]);
            total += a * b;
        }

        return total;
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);

        String oneLine = lines.stream().reduce("", (acc, line) -> acc + line);

        Pattern pattern = Pattern.compile("(?s)don't\\(\\).*?(?:do\\(\\)|$)|mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(oneLine);

        long total = 0;
        while (matcher.find()) {
            String group = matcher.group();
            if (!isNull(matcher.group(1))) {
                if (debug) LOG.info("a: {}, b: {}", matcher.group(1), matcher.group(2));
                long a = Long.parseLong(matcher.group(1));
                long b = Long.parseLong(matcher.group(2));
                total += a * b;
            }
            else if (debug) {
                LOG.info("group: {}", group);
            }
        }

        return total;
    }

}
