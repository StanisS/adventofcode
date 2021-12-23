package org.sstepanov.twenty.twenty.one;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Day5 {
    public static final String DATA_FILE = "Day5.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("1: " + part1());
        System.out.println("2: " + part2());
    }

    public static long part1() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        getLines().map(String::trim)
                .map(line -> line.split("->"))
                .forEach(points -> {
                    String[] point1 = points[0].trim().split(",");
                    String[] point2 = points[1].trim().split(",");

                    if (point1[0].equals(point2[0])) {
                        int y1 = Integer.parseInt(point1[1]);
                        int y2 = Integer.parseInt(point2[1]);
                        int min = Math.min(y1, y2);
                        int max = Math.max(y1, y2);
                        for (int i = min; i < max + 1; i++) {
                            result.compute(point1[0] + "," + i, (key, value) -> {
                                if (value == null) {
                                    return 1;
                                }
                                return ++value;
                            });
                        }
                    } else if (point1[1].equals(point2[1])) {
                        int x1 = Integer.parseInt(point1[0]);
                        int x2 = Integer.parseInt(point2[0]);
                        int min = Math.min(x1, x2);
                        int max = Math.max(x1, x2);
                        for (int i = min; i < max + 1; i++) {
                            result.compute(i + "," + point1[1], (key, value) -> {
                                if (value == null) {
                                    return 1;
                                }
                                return ++value;
                            });
                        }
                    }
                });
        return result.values().stream().filter(value -> value > 1).count();
    }

    public static long part2() throws Exception {
        Map<String, Integer> result = new HashMap<>();
        getLines().map(String::trim)
                .map(line -> line.split("->"))
                .forEach(points -> {
                    String[] point1 = points[0].trim().split(",");
                    String[] point2 = points[1].trim().split(",");

                    int x1 = Integer.parseInt(point1[0]);
                    int y1 = Integer.parseInt(point1[1]);
                    int x2 = Integer.parseInt(point2[0]);
                    int y2 = Integer.parseInt(point2[1]);
                    int f1 = x1 < x2 ? 1 : x1 == x2 ? 0 : -1;
                    int f2 = y1 < y2 ? 1 : y1 == y2 ? 0 : -1;
                    int length = f1 != 0 ? Math.abs(x1 - x2) : Math.abs(y1 - y2);
                    for (int i = 0; i < length + 1; i++) {
                        result.compute((x1 + (f1 * i)) + "," + (y1 + (f2 * i)), (key, value) -> {
                            if (value == null) {
                                return 1;
                            }
                            return ++value;
                        });
                    }
                });
        return result.values().stream().filter(value -> value > 1).count();
    }

    private static Stream<String> getLines() throws Exception {
        return Files.lines(Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(DATA_FILE)).toURI()));
    }

}
