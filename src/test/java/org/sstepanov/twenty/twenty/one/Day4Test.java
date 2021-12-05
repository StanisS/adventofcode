package org.sstepanov.twenty.twenty.one;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {

    @Test
    void part1() throws IOException {
        assertEquals(4512, Day4.part1());
    }

    @Test
    void part2() throws IOException {
        assertEquals(1924, Day4.part2());
    }
}
