package day09;

public class DiskRegion {
    int position;
    int size;
    boolean isFile;
    boolean wasMoved = false;
    DiskRegion previous;
    DiskRegion next;

    public DiskRegion(int position, int size, boolean isFile) {
        this.position = position;
        this.size = size;
        this.isFile = isFile;
    }

    public DiskRegion(int position, int size, boolean isFile, DiskRegion previous, DiskRegion next) {
        this.position = position;
        this.size = size;
        this.isFile = isFile;
        this.previous = previous;
        this.next = next;
        this.wasMoved = true;
    }
}
