package day11;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        return processFile(filepath, 25);
    }

    private long processFile(String filepath, int blinks) {
        List<String> lines = Input.readLines(filepath);
        Map<Long, Long> values = new HashMap<>();
        for (String part : lines.getFirst().split(" ")) {
            if (values.containsKey(Long.parseLong(part))) {
                values.put(Long.parseLong(part), values.get(Long.parseLong(part)) + 1);
            }
            else {
                values.put(Long.parseLong(part), 1L);
            }
        }

        for (int i = 0; i< blinks; i++) {
            values = blink(values);
            //printSummary(i, values);
        }

        long count = values.values().stream().mapToLong(Long::longValue).sum();
        return count;
    }

    private void printSummary(int iteration, Map<Long, Long> values) {
        long count = values.values().stream().mapToLong(Long::longValue).sum();
        System.out.println("Iteration " + iteration + " count: " + count);
    }

    private Map<Long, Long> blink(Map<Long, Long> values) {
        Map<Long, Long> newValues = new HashMap<>();

        values.forEach((key, count) -> {
            if (key == 0) {
                putOrAdd(newValues, 1L, count);
            }
            else if (Long.toString(key).length() % 2 == 0) {
                String valueAsText = Long.toString(key);
                putOrAdd(newValues, Long.parseLong(valueAsText.substring(0, valueAsText.length() / 2)), count);
                putOrAdd(newValues, Long.parseLong(valueAsText.substring(valueAsText.length() / 2)), count);
            }
            else {
                putOrAdd(newValues, key * 2024, count);
            }
        });

        return newValues;
    }

    private void putOrAdd(Map<Long, Long> map, long key, Long value) {
        map.put(key, map.getOrDefault(key, 0L) + value);
    }

    public long part2(String filepath, boolean debug) throws IOException {
        return processFile(filepath, 75);
    }
}
