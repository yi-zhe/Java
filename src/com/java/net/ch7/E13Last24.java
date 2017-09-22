package com.java.net.ch7;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class E13Last24 {

    public static void main(String[] args) throws IOException {

        Date today = new Date();
        long millisecondsPerDay = 24L * 60 * 60 * 1000;

        URL u = new URL("http://www.elharo.com");
        URLConnection uc = u.openConnection();
        System.out.println("Original if modified since " + new Date(uc.getIfModifiedSince()));
        uc.setIfModifiedSince((new Date(today.getTime() - millisecondsPerDay)).getTime());
        System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));

        try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        }

    }

}
