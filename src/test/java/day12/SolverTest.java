package day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverTest {

    @Test
    void part1() throws IOException {
        Solver solver = new Solver();
        assertEquals(140, solver.part1("day12/sample1.txt", false));
        assertEquals(772, solver.part1("day12/sample2.txt", false));
        assertEquals(1930, solver.part1("day12/sample3.txt", false));
        assertEquals(1304764, solver.part1("day12/input1.txt", false));
    }

    @Test
    void part2() throws IOException {
        Solver solver = new Solver();
        assertEquals(80, solver.part2("day12/sample1.txt", false));
        assertEquals(436, solver.part2("day12/sample2.txt", false));
        assertEquals(1206, solver.part2("day12/sample3.txt", false));
        assertEquals(236, solver.part2("day12/sample4.txt", false));
        assertEquals(368, solver.part2("day12/sample5.txt", false));
        assertEquals(811148, solver.part2("day12/input1.txt", false));
    }
}
