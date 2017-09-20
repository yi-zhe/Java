package com.java.net.ch7;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class E5AllHeaders {

    public static void main(String[] args) throws IOException {
        URL root = new URL("https://www.baidu.com");
        URLConnection uc = root.openConnection();

        for (int i = 1; ; i++) {
            String header = uc.getHeaderField(i);
            if (header == null) break;

            System.out.println(uc.getHeaderFieldKey(i) + ":" + header);
        }
    }

}
