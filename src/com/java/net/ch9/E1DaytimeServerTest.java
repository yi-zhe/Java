package com.java.net.ch9;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class E1DaytimeServerTest {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8090)) {
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            StringBuilder builder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
