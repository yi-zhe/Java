package com.java.net.ch7;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class E12URLPrinter {
    public static void main(String[] args) throws IOException {
        URL u = new URL("https://www.baidu.com");
        URLConnection uc = u.openConnection();
        System.out.println(uc.getURL());
    }
}
