package com.java.net.ch7;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class E4HeaderViewer {

    public static void main(String[] args) throws IOException {
        URL root = new URL("http://192.168.100.252:8080/apks/xinwenzhuan-production-2.5.1.0.apk");
        URLConnection uc = root.openConnection();

        System.out.println("Content-type:" + uc.getContentType());
        if (uc.getContentEncoding() != null) {
            System.out.println("Content-encoding:" + uc.getContentEncoding());
        }
        if (uc.getDate() != 0) {
            System.out.println("Date:" + new Date(uc.getDate()));
        }
        if (uc.getLastModified() != 0) {
            System.out.println("Last modified:" + new Date(uc.getLastModified()));
        }
        if (uc.getExpiration() != 0) {
            System.out.println("Expiration Date:" + new Date(uc.getExpiration()));
        }
        if (uc.getContentLength() != -1) {
            System.out.println("Content-length:" + uc.getContentLength());
        }
    }
}
