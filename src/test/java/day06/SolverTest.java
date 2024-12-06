package day06;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(41, solver.part1("day06/sample.txt", false));
        assertEquals(4890, solver.part1("day06/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(6, solver.part2("day06/sample.txt", false));
        assertEquals(1995, solver.part2("day06/input1.txt", false));
    }
}
