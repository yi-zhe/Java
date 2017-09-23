package com.java.net.ch9;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class E2TimeServer {

    public static void main(String[] args) {

        // Test class ch8/E3Time
        long differenceBetweenEpochs = 2208988800L;

        try (ServerSocket server = new ServerSocket(8090)) {
            while (true) {
                try (Socket socket = server.accept()) {
                    OutputStream out = socket.getOutputStream();

                    long millis = new Date().getTime()/1000 + differenceBetweenEpochs;
                    byte[] bytes = new byte[4];
                    bytes[0] = (byte) ((millis & 0x00000000FF000000L) >> 24);
                    bytes[1] = (byte) ((millis & 0x0000000000FF0000L) >> 16);
                    bytes[2] = (byte) ((millis & 0x000000000000FF00L) >> 8);
                    bytes[3] = (byte) ((millis & 0x00000000000000FFL));
                    out.write(bytes);
                    out.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
