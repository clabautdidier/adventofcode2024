package day04;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(18, solver.part1("day04/sample.txt", false));
        assertEquals(2504, solver.part1("day04/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(9, solver.part2("day04/sample.txt", false));
        assertEquals(1923, solver.part2("day04/input1.txt", false));
    }
}
