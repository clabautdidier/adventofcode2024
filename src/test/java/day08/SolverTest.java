package day08;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(14, solver.part1("day08/sample.txt", false));
        assertEquals(361, solver.part1("day08/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(34, solver.part2("day08/sample.txt", false));
        assertEquals(1249, solver.part2("day08/input1.txt", false));
    }
}
