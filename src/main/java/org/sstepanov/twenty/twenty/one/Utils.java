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

public class Utils {
    private static final String DATA_URL_PATTERN = "https://adventofcode.com/2021/day/%s/input";
    private static final String CLASS_FILE_PATTERN = "src/main/java/org/sstepanov/twenty/twenty/one/Day%s.java";
    private static final String DATA_FILE_PATTERN = "src/main/resources/Day%s.txt";
    private static final String TEST_CLASS_FILE_PATTERN = "src/test/java/org/sstepanov/twenty/twenty/one/Day%sTest.java";
    private static final String TEST_DATA_FILE_PATTERN = "src/test/resources/Day%s.txt";

//    Download data file for day [1 - the number of the challenge day; 2 - value of 'session' cookie from browser]
    public static void main(String[] args) throws IOException {
        downloadDataFile(args[0], args[1]);
        createClassFile(args[0]);
        creatTestClassFile(args[0]);
        creatTestDataFile(args[0]);
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
                                
                import java.io.BufferedReader;
                import java.io.IOException;
                import java.io.InputStreamReader;
                import java.util.Objects;
                                
                public class Day%1$s {
                    public static final String DATA_FILE = "/Day%1$s.txt";
                    
                    public static void main(String[] args) throws IOException {
                            System.out.println("1: " + part1());
                            System.out.println("2: " + part2());
                    }
                    
                     public static int part1() throws IOException {
                            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day%1$s.class.getResourceAsStream(DATA_FILE))));
                            return 0;
                     }
                     
                     public static int part2() throws IOException {
                            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day%1$s.class.getResourceAsStream(DATA_FILE))));
                            return 0;
                     }
                }
                """, day).getBytes(StandardCharsets.UTF_8)));
    }

    private static void creatTestClassFile(String day) throws IOException {
        FileOutputStream fos = new FileOutputStream(String.format(TEST_CLASS_FILE_PATTERN, day));
        fos.getChannel().write(ByteBuffer.wrap(String.format("""
                package org.sstepanov.twenty.twenty.one;
                 
                import org.junit.jupiter.api.Test;
                import java.io.IOException;
                
                import static org.junit.jupiter.api.Assertions.assertEquals;
                
                public class Day%1$sTest {
                
                    @Test
                    void part1() throws IOException {
                        assertEquals(-1, Day%1$s.part1());
                    }
                
                    @Test
                    void part2() throws IOException {
                        assertEquals(-1, Day%1$s.part2());
                    }
                }
                """, day).getBytes(StandardCharsets.UTF_8)));
    }

    private static void creatTestDataFile(String day) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(String.format(TEST_DATA_FILE_PATTERN, day));

    }

}
