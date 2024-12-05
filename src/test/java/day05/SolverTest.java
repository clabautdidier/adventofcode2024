package day05;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(143, solver.part1("day05/sample.txt", false));
        assertEquals(5948, solver.part1("day05/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(123, solver.part2("day05/sample.txt", false));
        assertEquals(3062, solver.part2("day05/input1.txt", false));
    }
}
