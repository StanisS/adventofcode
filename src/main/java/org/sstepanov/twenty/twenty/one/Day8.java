package org.sstepanov.twenty.twenty.one;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * 1:       |  7:      |  4:      |  2:      3:       5:     |  0:      6:      9:      |   8:
 * ....    |   aaaa   |   ....   |   aaaa    aaaa    aaaa   |   aaaa    aaaa    aaaa   |    aaaa
 * .    c   |  .    c  |  b    c  |  .    c  .    c  b    .  |  b    c  b    .  b    c  |   b    c
 * .    c   |  .    c  |  b    c  |  .    c  .    c  b    .  |  b    c  b    .  b    c  |   b    c
 * ....    |   ....   |   dddd   |   dddd    dddd    dddd   |   ....    dddd    dddd   |    dddd
 * .    f   |  .    f  |  .    f  |  e    .  .    f  .    f  |  e    f  e    f  .    f  |   e    f
 * .    f   |  .    f  |  .    f  |  e    .  .    f  .    f  |  e    f  e    f  .    f  |   e    f
 * ....    |   ....   |   ....   |   gggg    gggg    gggg   |   gggg    gggg    gggg   |    gggg
 * ------------------------------------------------------------------------------------------
 * 0-6; 1-2; 2-5; 3-5; 4-4;
 * 5-5; 6-6; 7-3; 8-7; 9-6;
 */

public class Day8 {
    private static final String DATA_FILE = "Day8.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("1: " + part1());
        System.out.println("2: " + part2());
    }

    public static long part1() throws Exception {
        return getLines().map(line -> line.trim().split("[|]")[1])
                .flatMap(digits -> Stream.of(digits.trim().split(" ")))
                .filter(digit -> {
                    int length = digit.trim().length();
                    return length == 2 || length == 4 || length == 3 || length == 7;
                })
                .count();
    }

    public static long part2() throws Exception {
        AtomicLong sum = new AtomicLong();
        getLines().map(line -> line.trim().split("[|]"))
                .forEach(line -> {
                    char[] dictionary = getDictionary(line[0]);
                    String[] digits = line[1].trim().split(" ");
                    int thousands = decodeDigit(digits[0], dictionary);
                    int hundreds = decodeDigit(digits[1], dictionary);
                    int tens = decodeDigit(digits[2], dictionary);
                    int ones = decodeDigit(digits[3], dictionary);
                    sum.addAndGet(thousands * 1000 + hundreds * 100 + tens * 10 + ones);
                });
        return sum.get();
    }

    private static int decodeDigit(String digit, char[] dictionary) {
        digit = digit.trim();
        return switch (digit.length()) {
            case 2 -> 1;
            case 3 -> 7;
            case 4 -> 4;
            case 5 -> {
                boolean[] tmp = checkCharacter(digit, dictionary);
                if (tmp[0] && tmp[1]) {
                    yield 3;
                }
                if (tmp[2] && tmp[3]) {
                    yield 5;
                }
                yield 2;
            }
            case 6 -> {
                boolean[] tmp = checkCharacter(digit, dictionary);
                if (tmp[0] && tmp[1] && tmp[2] && tmp[3]) {
                    yield 9;
                }
                if (tmp[2] && tmp[3]) {
                    yield 6;
                }
                yield 0;
            }
            case 7 -> 8;
            default -> throw new IllegalStateException("Unexpected value: " + digit.length());
        };
    }

    private static boolean[] checkCharacter(String digit, char[] dictionary) {
        boolean[] tmp = new boolean[4];
        for (char ch : digit.toCharArray()) {
            if (ch == dictionary[0]) {
                tmp[0] = true;
            } else if (ch == dictionary[1]) {
                tmp[1] = true;
            } else if (ch == dictionary[2]) {
                tmp[2] = true;
            } else if (ch == dictionary[3]) {
                tmp[3] = true;
            }
        }
        return tmp;
    }

    private static char[] getDictionary(String s) {
        char[][] result = new char[2][];
        String[] digits = s.trim().split(" ");
        String[] tmp = new String[2];
        for (String digit : digits) {
            String trim = digit.trim();
            if (trim.length() == 2) {
                tmp[0] = trim;
            } else if (trim.length() == 4) {
                tmp[1] = trim;
            }
        }

        result[0] = tmp[0].toCharArray();
        result[1] = tmp[1].replaceAll(String.format("[%s%s]", result[0][0], result[0][1]), "")
                .toCharArray();

        return new char[]{result[0][0], result[0][1], result[1][0], result[1][1]};
    }

    private static Stream<String> getLines() throws Exception {
        return Files.lines(Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(DATA_FILE)).toURI()));
    }
}
