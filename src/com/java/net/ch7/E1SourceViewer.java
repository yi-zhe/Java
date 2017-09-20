package com.java.net.ch7;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class E1SourceViewer {

    public static void main(String[] args) throws IOException {
        URL u = new URL("https://www.baidu.com");
        URLConnection uc = u.openConnection();
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
