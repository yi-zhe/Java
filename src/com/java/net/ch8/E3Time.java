package com.java.net.ch8;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

public class E3Time {

    public static void main(String[] args) throws IOException {
        long differenceBetweenEpochs = 2208988800L; // 1900-01-01 00:00:00 ~ 1970-01-01 00:00:00

        try (Socket socket = new Socket("time.nist.gov", 37)) {
            socket.setSoTimeout(3000);

            InputStream raw = socket.getInputStream();
            long secondsSince1900 = 0;
            for (int i = 0; i < 4; i++) {
                secondsSince1900 = (secondsSince1900 << 8) | raw.read();
            }

            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
            Date time = new Date(1000L * secondsSince1970);

            System.out.println(time);
        }
    }
}
