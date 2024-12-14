package day12;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        Map<Point, GardenPlot> garden = parseGarden(lines);

        processGarden(lines, garden);

        Map<String, PlotPrice> gardenPrice = calculatePlotPrices(garden);

        long sum = 0;
        for (PlotPrice price : gardenPrice.values()) {
            sum += (long) price.getArea() * price.getFenceLength();
        }
        return sum;
    }

    public long part2(String filepath, boolean debug) throws IOException {
        List<String> lines = Input.readLines(filepath);
        Map<Point, GardenPlot> garden = parseGarden(lines);

        processGarden(lines, garden);

        Map<String, PlotPrice> gardenPrice = calculatePlotPrices(garden);

        long sum = 0;
        for (PlotPrice price : gardenPrice.values()) {
            sum += (long) price.getArea() * price.getCorners();
        }
        return sum;
    }

    private Map<String, PlotPrice> calculatePlotPrices(Map<Point, GardenPlot> garden) {
        Map<String, PlotPrice> gardenPrice = new HashMap<>();
        for (Map.Entry<Point, GardenPlot> plotEntry : garden.entrySet()) {
            String key = "" + plotEntry.getValue().letter() + plotEntry.getValue().groupId();
            int fenceLength = calculateFenceLength(garden, plotEntry.getKey(), plotEntry.getValue().letter());
            int corners = calculateCorners(garden, plotEntry.getKey(), plotEntry.getValue().letter());

            if (gardenPrice.containsKey(key)) {
                PlotPrice price = gardenPrice.get(key);
                price.addArea();
                price.addFenceLength(fenceLength);
                price.addCorners(corners);
            } else {
                gardenPrice.put(key, new PlotPrice(fenceLength, corners));
            }
        }
        return gardenPrice;
    }


    private void processGarden(List<String> lines, Map<Point, GardenPlot> garden) {
        int counter = 1;
        for (int y = 0; y< lines.size(); y++) {
            for (int x = 0; x < lines.getFirst().length(); x++) {
                GardenPlot plot = garden.get(new Point(x, y));
                if (plot.groupId() == 0) {
                    processPlot(garden, plot, x, y, counter++, lines.getFirst().length(), lines.size());
                }
            }
        }
    }

    private int calculateCorners(Map<Point, GardenPlot> garden, Point key, Character letter) {
        int corners = 0;

        if (isOutsidePlot(garden, letter, new Point(key.x-1, key.y)) && isOutsidePlot(garden, letter, new Point(key.x, key.y-1))) corners++;
        if (isOutsidePlot(garden, letter, new Point(key.x, key.y-1)) && isOutsidePlot(garden, letter, new Point(key.x+1, key.y))) corners++;
        if (isOutsidePlot(garden, letter, new Point(key.x+1, key.y)) && isOutsidePlot(garden, letter, new Point(key.x, key.y+1))) corners++;
        if (isOutsidePlot(garden, letter, new Point(key.x, key.y+1)) && isOutsidePlot(garden, letter, new Point(key.x-1, key.y))) corners++;

        if (isInsidePlot(garden, letter, new Point(key.x-1, key.y))
                && isInsidePlot(garden, letter, new Point(key.x, key.y-1))
                && isOutsidePlot(garden, letter, new Point(key.x-1, key.y-1))) corners++;

        if (isInsidePlot(garden, letter, new Point(key.x+1, key.y))
                && isInsidePlot(garden, letter, new Point(key.x, key.y-1))
                && isOutsidePlot(garden, letter, new Point(key.x+1, key.y-1))) corners++;

        if (isInsidePlot(garden, letter, new Point(key.x+1, key.y))
                && isInsidePlot(garden, letter, new Point(key.x, key.y+1))
                && isOutsidePlot(garden, letter, new Point(key.x+1, key.y+1))) corners++;

        if (isInsidePlot(garden, letter, new Point(key.x-1, key.y))
                && isInsidePlot(garden, letter, new Point(key.x, key.y+1))
                && isOutsidePlot(garden, letter, new Point(key.x-1, key.y+1))) corners++;

        return corners;
    }

    private int calculateFenceLength(Map<Point, GardenPlot> garden, Point key, char letter) {
        int fenceLength = 0;

        if (isOutsidePlot(garden, letter, new Point(key.x, key.y - 1))) fenceLength++;
        if (isOutsidePlot(garden, letter, new Point(key.x+1, key.y))) fenceLength++;
        if (isOutsidePlot(garden, letter, new Point(key.x, key.y+1))) fenceLength++;
        if (isOutsidePlot(garden, letter, new Point(key.x-1, key.y))) fenceLength++;

        return fenceLength;
    }

    private static boolean isOutsidePlot(Map<Point, GardenPlot> garden, char letter, Point point) {
        return !garden.containsKey(point) || garden.get(point).letter() != letter;
    }

    private static boolean isInsidePlot(Map<Point, GardenPlot> garden, char letter, Point point) {
        return garden.containsKey(point) && garden.get(point).letter() == letter;
    }

    private void processPlot(Map<Point, GardenPlot> garden, GardenPlot plot, int x, int y, int newId, int maxX, int maxY) {
        garden.put(new Point(x, y), new GardenPlot(plot.letter(), newId));

        processPlotIfSameAndNotVisited(garden, plot, x, y-1, newId, maxX, maxY);
        processPlotIfSameAndNotVisited(garden, plot, x+1, y, newId, maxX, maxY);
        processPlotIfSameAndNotVisited(garden, plot, x, y+1, newId, maxX, maxY);
        processPlotIfSameAndNotVisited(garden, plot, x-1, y, newId, maxX, maxY);
    }

    private void processPlotIfSameAndNotVisited(Map<Point, GardenPlot> garden, GardenPlot plot, int x, int y, int newId, int maxX, int maxY) {
        if (y>=0 && y<maxY && x>=0 && x<maxX) {
            GardenPlot otherPlot = garden.get(new Point(x, y));
            if (otherPlot.letter() == plot.letter() && otherPlot.groupId() < newId) {
                processPlot(garden, otherPlot, x, y, newId, maxX, maxY);
            }
        }
    }

    private Map<Point, GardenPlot> parseGarden(List<String> lines) {
        Map<Point, GardenPlot> garden = new HashMap<>();
        for (int y=0; y<lines.size(); y++) {
            String line = lines.get(y);
            for (int x=0; x<line.length(); x++) {
                char c = line.charAt(x);
                garden.put(new Point(x, y), new GardenPlot(c, 0));
            }
        }
        return garden;
    }

}
