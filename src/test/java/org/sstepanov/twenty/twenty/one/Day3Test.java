package org.sstepanov.twenty.twenty.one;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {

    @Test
    void one() throws IOException {
        assertEquals(198, Day3.diagnostic1());
    }

    @Test
    void two() throws IOException {
        assertEquals(230, Day3.diagnostic2());
    }
}
