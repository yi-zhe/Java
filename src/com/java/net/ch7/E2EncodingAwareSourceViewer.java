package com.java.net.ch7;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class E2EncodingAwareSourceViewer {

    public static void main(String[] args) throws IOException {
        String encoding = "ISO-8859-1";
        URL u = new URL("https://www.baidu.com");
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int encodingStart = contentType.indexOf("charset=");
        if (encodingStart != -1) {
            encoding = contentType.substring(encodingStart + 8);
        }
        InputStream in = new BufferedInputStream(uc.getInputStream());
        Reader reader = new InputStreamReader(in, encoding);
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
        reader.close();
    }

}
