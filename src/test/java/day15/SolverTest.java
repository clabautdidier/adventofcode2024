package day15;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(2028, solver.part1("day15/sample1.txt", true));
        assertEquals(10092, solver.part1("day15/sample2.txt", false));
        assertEquals(1383666, solver.part1("day15/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(9021, solver.part2("day15/sample2.txt", true));
//        assertEquals(99999, solver.part2("day15/input1.txt", false));
    }
}
