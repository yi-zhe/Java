package com.java.net.ch8;

import java.io.IOException;
import java.net.Socket;

public class E4PortScanner {

    public static void main(String[] args) {
        for (int i = 20; i < 25; i++) {
            try {
                Socket socket = new Socket("192.168.0.200", i);
                System.out.println("There is a server on port " + i);
                socket.close();
            } catch (IOException e) {
                System.out.println("There is no server on port " + i);
            }
        }
    }
}
