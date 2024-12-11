package day09;

public class BlockFeed {
    private String line;
    int position = 0;
    int count = 0;
    private final int step;

    public BlockFeed(String line, int step) {
        this.line = line;
        this.count = Integer.parseInt(line.charAt(position) + "");
        this.step = step;
    }

    public int getLinePosition() {
        return position;
    }

    public int next() {
        count --;
        while (count <= 0 || Integer.parseInt(line.charAt(position) + "") == 0) {
            position+=step;
            if (position >= line.length()) {
                return -1;
            }
            count = Integer.parseInt(line.charAt(position) + "");
        }

        return position * 10 + count;
    }

    public int getBlockPosition() {
        return (line.length() + 1) / 2 - 1 - (getLinePosition() / 2);
    }

    public boolean hasNextInPosition() {
        return count > 1;
    }
}
