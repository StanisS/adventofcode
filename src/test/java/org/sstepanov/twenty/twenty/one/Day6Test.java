package org.sstepanov.twenty.twenty.one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

    @Test
    void part1() throws Exception {
        assertEquals(5934, Day6.part1());
    }

    @Test
    void part2() throws Exception {
        assertEquals(26984457539L, Day6.part2());
    }

    @Test
    void count() throws Exception {
        assertEquals(26, Day6.count(18));
    }
}
