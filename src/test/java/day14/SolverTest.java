package day14;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(12, solver.part1("day14/sample.txt", false, 11, 7));
        assertEquals(225810288, solver.part1("day14/input1.txt", false, 101, 103));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(6752, solver.part2("day14/input1.txt", false));
    }
}
