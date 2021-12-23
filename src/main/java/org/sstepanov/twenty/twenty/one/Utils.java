package org.sstepanov.twenty.twenty.one;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    private static final String DATA_URL_PATTERN = "https://adventofcode.com/2021/day/%s/input";
    private static final String CLASS_FILE_PATTERN = "src/main/java/org/sstepanov/twenty/twenty/one/Day%s.java";
    private static final String DATA_FILE_PATTERN = "src/main/resources/Day%s.txt";
    private static final String TEST_CLASS_FILE_PATTERN = "src/test/java/org/sstepanov/twenty/twenty/one/Day%sTest.java";
    private static final String TEST_DATA_FILE_PATTERN = "src/test/resources/Day%s.txt";

//    Download data file for day [value of 'session' cookie from browser]
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter day");
        String day = scanner.nextLine();

        downloadDataFile(day, args[0]);
        createClassFile(day);
        creatTestClassFile(day);
        creatTestDataFile(day);
    }

    private static void downloadDataFile(String day, String session) throws IOException {
        URLConnection connection = new URL(String.format(DATA_URL_PATTERN, day)).openConnection();
        connection.setRequestProperty("Cookie", "session=" + session);
        ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
        FileOutputStream fos = new FileOutputStream(String.format(DATA_FILE_PATTERN, day));
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    private static void createClassFile(String day) throws IOException {
        FileOutputStream fos = new FileOutputStream(String.format(CLASS_FILE_PATTERN, day));
        fos.getChannel().write(ByteBuffer.wrap(String.format("""
                package org.sstepanov.twenty.twenty.one;
                                
                import java.nio.file.Files;
                import java.nio.file.Path;
                import java.util.HashMap;
                import java.util.Map;
                import java.util.Objects;
                import java.util.stream.Stream;
                                
                public class Day%1$s {
                    public static final String DATA_FILE = "Day%1$s.txt";
                    
                    public static void main(String[] args) throws Exception {
                            System.out.println("1: " + part1());
                            System.out.println("2: " + part2());
                    }
                    
                     public static int part1() throws Exception {
                            getLines();
                            return 0;
                     }
                     
                     public static int part2() throws Exception {
                            getLines();
                            return 0;
                     }
                     
                     private static Stream<String> getLines() throws IOException, URISyntaxException {
                             return Files.lines(Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(DATA_FILE)).toURI()));
                     }
                }
                """, day).getBytes(StandardCharsets.UTF_8)));
    }

    private static void creatTestClassFile(String day) throws IOException {
        FileOutputStream fos = new FileOutputStream(String.format(TEST_CLASS_FILE_PATTERN, day));
        fos.getChannel().write(ByteBuffer.wrap(String.format("""
                package org.sstepanov.twenty.twenty.one;
                 
                import org.junit.jupiter.api.Test;
                
                import static org.junit.jupiter.api.Assertions.assertEquals;
                
                public class Day%1$sTest {
                
                    @Test
                    void part1() throws Exception {
                        assertEquals(-1, Day%1$s.part1());
                    }
                
                    @Test
                    void part2() throws Exception {
                        assertEquals(-1, Day%1$s.part2());
                    }
                }
                """, day).getBytes(StandardCharsets.UTF_8)));
    }

    private static void creatTestDataFile(String day) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(String.format(TEST_DATA_FILE_PATTERN, day));

    }

}
