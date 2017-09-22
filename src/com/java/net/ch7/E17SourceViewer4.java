package com.java.net.ch7;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class E17SourceViewer4 {

    public static void main(String[] args) throws IOException {
        URL u = new URL("https://www.baidu.com/df");
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();

        System.out.println(uc.getResponseCode());

        try (InputStream raw = uc.getInputStream()) {
            printFromStream(raw);
        } catch (Exception e) {
            printFromStream(uc.getErrorStream());
        }
    }

    private static void printFromStream(InputStream errorStream) throws IOException {
        InputStream buffer = new BufferedInputStream(errorStream);
        Reader reader = new InputStreamReader(buffer);
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
