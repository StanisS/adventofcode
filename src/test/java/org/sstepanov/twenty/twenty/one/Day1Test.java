package org.sstepanov.twenty.twenty.one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day1Test {
    @Test
    void one() throws IOException {
        Assertions.assertEquals(7, Day1.count1());
    }

    @Test
    void two() throws IOException {
        Assertions.assertEquals(5, Day1.count2());
    }
}
