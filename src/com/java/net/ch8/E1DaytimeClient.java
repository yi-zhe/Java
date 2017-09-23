package com.java.net.ch8;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class E1DaytimeClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(3000);

            InputStream in = socket.getInputStream();
            StringBuilder builder = new StringBuilder();

            InputStreamReader reader = new InputStreamReader(in);

            for (int c = reader.read(); c != -1; c = reader.read()) {
                builder.append((char) c);
            }

            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
