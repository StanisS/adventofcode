package org.sstepanov.twenty.twenty.one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day2Test {
    @Test
    void one() throws IOException {
        Assertions.assertEquals(150, Day2.position1());
    }

    @Test
    void two() throws IOException {
        Assertions.assertEquals(900, Day2.position2());
    }
}
