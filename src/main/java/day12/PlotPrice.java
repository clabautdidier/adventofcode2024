package day12;

public class PlotPrice {
    private int area;
    private int fenceLength;
    private int corners;

    public PlotPrice(int fenceLength, int corners) {
        this.area = 1;
        this.corners = corners;
        this.fenceLength = fenceLength;
    }

    public void addArea() {
        area++;
    }

    public void addFenceLength(int fenceLength) {
        this.fenceLength += fenceLength;
    }

    public int getArea() {
        return area;
    }

    public int getFenceLength() {
        return fenceLength;
    }

    public int getCorners() {
        return corners;
    }

    public void addCorners(int corners) {
        this.corners += corners;
    }
}
