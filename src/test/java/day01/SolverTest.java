package day01;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(11, solver.part1("day01/sample.txt", false));
        assertEquals(1151792, solver.part1("day01/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(31, solver.part2("day01/sample.txt", false));
        assertEquals(21790168, solver.part2("day01/input1.txt", false));
    }
}
