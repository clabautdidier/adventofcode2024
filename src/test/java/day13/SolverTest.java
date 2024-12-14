package day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(480, solver.part1("day13/sample.txt", false));
        assertEquals(29201, solver.part1("day13/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(104140871044942L, solver.part2("day13/input1.txt", false));
    }
}
