package org.hackathon.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vh on 11/18/17.
 */
public class IOHelper {

    private static int DEFAULT_BUFFER_SIZE = 1024;

    public static void transfer(InputStream in, OutputStream out, byte[] buffer) throws IOException {
        int count;
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
    }

    public static void transfer(InputStream in, OutputStream out, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        transfer(in, out, buffer);
    }

    public static byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        transfer(in, out, DEFAULT_BUFFER_SIZE);

        return out.toByteArray();
    }

    public static String post(String url, String body) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        conn.getOutputStream().write(body.getBytes());
        byte[] bytes = IOHelper.readFully(conn.getInputStream());

        return new String(bytes);
    }

}