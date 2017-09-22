package com.java.net.ch7;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class E16SourceViewer3 {


    public static void main(String[] args) throws IOException {
        URL u = new URL("https://www.baidu.com");
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();
        int code = uc.getResponseCode();
        String response = uc.getResponseMessage();

        System.out.println("HTTP/1.x " + code + " " + response);

        for (int i = 1; ; i++) {
            String header = uc.getHeaderField(i);
            String key = uc.getHeaderFieldKey(i);
            if (header == null || key == null) break;

            System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
        }

        System.out.println();

        try (InputStream raw = uc.getInputStream()) {
            InputStream buffer = new BufferedInputStream(raw);
            Reader reader = new InputStreamReader(buffer);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }

}
