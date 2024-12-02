package day02;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(2, solver.part1("day02/sample.txt", false));
        assertEquals(390, solver.part1("day02/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(4, solver.part2("day02/sample.txt", false));
        assertEquals(439, solver.part2("day02/input1.txt", false));
    }
}
