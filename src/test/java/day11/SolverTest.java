package day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(55312, solver.part1("day11/sample.txt", false));
        assertEquals(187738, solver.part1("day11/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(223767210249237L, solver.part2("day11/input1.txt", false));
    }
}
