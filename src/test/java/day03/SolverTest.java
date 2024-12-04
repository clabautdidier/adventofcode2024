package day03;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(161, solver.part1("day03/sample.txt", false));
        assertEquals(156388521, solver.part1("day03/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(48, solver.part2("day03/sample.txt", false));
        assertEquals(75920122, solver.part2("day03/input1.txt", true));
    }
}
