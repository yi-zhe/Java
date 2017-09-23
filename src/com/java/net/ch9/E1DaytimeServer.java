package com.java.net.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class E1DaytimeServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8090)) {
            while (true) {
                try (Socket socket = server.accept()) {
                    System.out.println("Request from " + socket.getRemoteSocketAddress());
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                    writer.write(new Date().toString() + "\r\n");
                    writer.flush();
                    writer.close();
                    System.out.println("Handled request from " + socket.getRemoteSocketAddress());
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
