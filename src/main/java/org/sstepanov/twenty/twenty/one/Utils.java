package org.sstepanov.twenty.twenty.one;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Utils {
    private static final String DATA_URL_PATTERN = "https://adventofcode.com/2021/day/%s/input";
    private static final String SRC_MAIN_RESOURCES = "src/main/resources";

//    Download data file for day [1 - the number of the challenge day; 2 - value of 'session' cookie from browser]
    public static void main(String[] args) throws IOException {
        Utils.downloadDataFile(args[0], args[1]);
    }

    private static void downloadDataFile(String day, String session) throws IOException {
        URLConnection connection = new URL(String.format(DATA_URL_PATTERN, day)).openConnection();
        connection.setRequestProperty("Cookie", "session=" + session);
        ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
        FileOutputStream fos = new FileOutputStream(SRC_MAIN_RESOURCES + "/Day" + day + ".txt");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    }
}
