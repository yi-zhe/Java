package com.java.net.ch7;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class E3BinarySaver {

    public static void main(String[] args) throws IOException {
        URL root = new URL("http://192.168.100.252:8080/apks/xinwenzhuan-production-2.5.1.0.apk");
        URLConnection uc = root.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/")) {
            throw new IOException("not a binary file");
        }

        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;

            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                offset += bytesRead;
            }

            if (offset != contentLength) {
                throw new IOException("not fully read");
            }

            String filename = root.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }
        }
    }

}
