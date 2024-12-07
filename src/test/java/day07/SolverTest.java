package day07;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(3749, solver.part1("day07/sample.txt", false));
        assertEquals(20281182715321L, solver.part1("day07/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(11387, solver.part2("day07/sample.txt", false));
        assertEquals(159490400628354L, solver.part2("day07/input1.txt", false));
    }
}
