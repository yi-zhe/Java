package com.java.net.ch5;

import java.io.*;
import java.net.URL;

public class E2SourceViewer {

    public static void main(String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("https://www.baidu.com");
            in = u.openStream();
            in = new BufferedInputStream(in);
            Reader reader = new InputStreamReader(in);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
