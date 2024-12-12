package day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(36, solver.part1("day10/sample.txt", false));
        assertEquals(548, solver.part1("day10/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(81, solver.part2("day10/sample.txt", false));
        assertEquals(1252, solver.part2("day10/input1.txt", false));
    }
}
