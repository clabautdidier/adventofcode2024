package day05;

import common.Input;
import common.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public int part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        SourceData sourceData = getSourceData(lines);

        int sum = 0;
        for (List<Integer> page : sourceData.pages()) {
            boolean ok = true;

            for (int i = 0; i < page.size()-1 && ok; i++) {
                for (int j = i+1; j<page.size(); j++) {
                    if (isWrongOrder(sourceData.rules(), page, i, j)) {
                        if (debug) System.out.println("Incorrect: " + page.get(i) + " -> " + page.get(j));
                        ok = false;
                        break;
                    }
                }
            }
            if (!ok) {
                continue;
            }
            if (debug) System.out.println("Correct: " + page + " += " + page.get(page.size()/2));
            sum += page.get(page.size()/2);
        }
        return sum;
    }

    private static SourceData getSourceData(List<String> lines) {
        Set<Tuple<Integer,Integer>> rules = new HashSet<>();

        int line=0;
        while (!lines.get(line).isEmpty()) {
            String[] parts = lines.get(line).split("\\|");
            Tuple<Integer, Integer> tuple = new Tuple<>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            rules.add(tuple);
            line++;
        }

        List<List<Integer>> pages = new ArrayList<>();
        line++;
        while (line < lines.size()) {
            String[] parts = lines.get(line).split(",");
            List<Integer> page = new ArrayList<>();
            for (String part : parts) {
                page.add(Integer.parseInt(part));
            }
            pages.add(page);
            line++;
        }
        return new SourceData(rules, pages);
    }

    private record SourceData(Set<Tuple<Integer, Integer>> rules, List<List<Integer>> pages) {
    }

    private static boolean isWrongOrder(Set<Tuple<Integer, Integer>> rules, List<Integer> page, int left, int right) {
        return rules.contains(new Tuple<>(page.get(right), page.get(left)));
    }

    public int part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        SourceData sourceData = getSourceData(lines);

        int sum = 0;
        for (List<Integer> page : sourceData.pages()) {

            boolean ok = false;
            boolean wasSwitched = false;

            while (!ok) {
                ok = true;
                for (int i = 0; i < page.size() - 1 && ok; i++) {
                    for (int j = i + 1; j < page.size(); j++) {
                        if (isWrongOrder(sourceData.rules(), page, i, j)) {
                            if (debug) System.out.println("Incorrect: " + page.get(i) + " -> " + page.get(j));
                            page = switchElements(page, i, j);
                            ok = false;
                            wasSwitched = true;
                            break;
                        }
                    }
                }
            }
            if (debug) System.out.println("Correct: " + page + " += " + page.get(page.size()/2));
            if (wasSwitched) sum += page.get(page.size()/2);
        }
        return sum;
    }

    private List<Integer> switchElements(List<Integer> list, int left, int right) {
        List<Integer> copy = new ArrayList<>(list);
        int temp = copy.get(left);
        copy.set(left, copy.get(right));
        copy.set(right, temp);
        return copy;
    }
}
