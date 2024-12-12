package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainedTest {

    @Test
    void insertRight() {
        Chained<String> root = new Chained<>("root");
        Chained<String> right = root.insertRight("right");
        assertEquals("right", root.getNext().getValue());
        assertEquals("root", right.getPrevious().getValue());

        Chained<String> innerright = root.insertRight("innerright");
        assertEquals("innerright", root.getNext().getValue());
        assertEquals("root", innerright.getPrevious().getValue());
        assertEquals("right", innerright.getNext().getValue());
    }

    @Test
    void insertLeft() {
        Chained<String> root = new Chained<>("root");
        Chained<String> left = root.insertLeft("left");
        assertEquals("left", root.getPrevious().getValue());
        assertEquals("root", left.getNext().getValue());
    }
}