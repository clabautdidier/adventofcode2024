package common;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        Tuple<Integer, Integer> tuple = new Tuple<>(1, 2);
        assertEquals("Tuple{x=1, y=2}", tuple.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Tuple<Integer, Integer> tuple1 = new Tuple<>(1, 2);
        Tuple<Integer, Integer> tuple2 = new Tuple<>(1, 2);
        Tuple<Integer, Integer> tuple3 = new Tuple<>(2, 2);
        Tuple<Integer, Integer> tuple4 = new Tuple<>(1, 3);
        assertTrue(tuple1.equals(tuple2));
        assertFalse(tuple1.equals(tuple3));
        assertFalse(tuple1.equals(tuple4));

        Set<Tuple<Integer, Integer>> set = new HashSet<>();
        set.add(tuple1);
        set.add(tuple2);
        set.add(tuple3);
        set.add(tuple4);
        assertEquals(3, set.size());
    }

}