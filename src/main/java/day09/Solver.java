package day09;

import common.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    Logger LOG = LoggerFactory.getLogger(Solver.class);

    public long part1(String filepath, boolean debug) throws IOException {
        String line = Input.readLines(filepath).getFirst();

        BlockFeed blockFeedLeft = new BlockFeed(line, 1);
        BlockFeed blockFeedRight = new BlockFeed(new StringBuilder(line).reverse().toString(), 2);

        int position = 0;
        long result = 0;

        while (blockFeedLeft.getLinePosition() / 2 < blockFeedRight.getBlockPosition()) {
            if (blockFeedLeft.getLinePosition() % 2 == 0) {
                int blockPosition = blockFeedLeft.getLinePosition() / 2;
                result += (long) blockPosition * position;
            }
            else {
                result += (long) blockFeedRight.getBlockPosition() * position;
                blockFeedRight.next();
            }
            blockFeedLeft.next();
            position++;
        }

        while (blockFeedLeft.getLinePosition() / 2 == blockFeedRight.getBlockPosition()) {
            if (blockFeedLeft.getLinePosition() % 2 == 0) {
                int blockPosition = blockFeedLeft.getLinePosition() / 2;
                result += (long) blockPosition * position;
            }
            else {
                result += (long) blockFeedRight.getBlockPosition() * position;
            }
            blockFeedRight.next();
            blockFeedLeft.next();
            position++;
        }
        return result;
    }

    public long part2(String filepath, boolean debug) throws IOException {
        String line = Input.readLines(filepath).getFirst();

        DiskRegion firstDiskRegion = new DiskRegion(0, Integer.parseInt(line.charAt(0) + ""), true);
        DiskRegion lastDiskRegion = firstDiskRegion;

        for (int i = 1; i < line.length(); i++) {
            DiskRegion diskRegion = new DiskRegion(i / 2, Integer.parseInt(line.charAt(i) + ""), i % 2 == 0);
            diskRegion.previous = lastDiskRegion;
            lastDiskRegion.next = diskRegion;
            lastDiskRegion = diskRegion;
        }

        if (debug) printDisk(firstDiskRegion);

        while (lastDiskRegion.previous != null) {
            DiskRegion currentDiskRegion = firstDiskRegion.next;
            while (currentDiskRegion.wasMoved || currentDiskRegion.position < lastDiskRegion.position) {
                if (currentDiskRegion == lastDiskRegion) {
                    break;
                }
                if (!currentDiskRegion.isFile && currentDiskRegion.size >= lastDiskRegion.size) {
                    currentDiskRegion.previous.next = new DiskRegion(lastDiskRegion.position, lastDiskRegion.size, true, currentDiskRegion.previous, currentDiskRegion);
                    currentDiskRegion.previous = currentDiskRegion.previous.next;
                    currentDiskRegion.size = currentDiskRegion.size - lastDiskRegion.size;
                    lastDiskRegion.isFile = false;
                    break;
                }
                currentDiskRegion = currentDiskRegion.next;
            }

            do {
                lastDiskRegion = lastDiskRegion.previous;
            } while (lastDiskRegion.previous != null && !lastDiskRegion.isFile);
            if (debug) printDisk(firstDiskRegion);
        }

        int position = 0;
        long result = 0;
        DiskRegion currentDiskRegion = firstDiskRegion;
        while (currentDiskRegion != null) {
            if (currentDiskRegion.isFile) {
                for (int i=0; i < currentDiskRegion.size; i++) {
                    result += (long) currentDiskRegion.position * position;

                    position++;
                }
            }
            else position += currentDiskRegion.size;

            currentDiskRegion = currentDiskRegion.next;
        }

        return result;
    }

    private static void printDisk(DiskRegion firstDiskRegion) {
        DiskRegion currentDiskRegion = firstDiskRegion;
        while (currentDiskRegion != null) {

            for (int i=0; i<currentDiskRegion.size; i++) {
                if (currentDiskRegion.isFile) {
                    System.out.print(currentDiskRegion.position);
                }
                else {
                    System.out.print(".");
                }
            }

            currentDiskRegion = currentDiskRegion.next;
        }
        System.out.println();
    }
}
