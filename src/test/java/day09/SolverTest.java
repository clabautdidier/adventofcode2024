package day09;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(1928, solver.part1("day09/sample.txt", false));
        assertEquals(6356833654075L, solver.part1("day09/input1.txt", true));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(2858, solver.part2("day09/sample.txt", false));
        assertEquals(6389911791746L, solver.part2("day09/input1.txt", false));
    }
}
