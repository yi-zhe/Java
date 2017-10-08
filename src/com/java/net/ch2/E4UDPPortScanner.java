package com.java.net.ch2;

import java.net.DatagramSocket;
import java.net.SocketException;

public class E4UDPPortScanner {

    public static void main(String[] args) {
        for (int port = 0; port <= 65535; port++) {
            try {
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            } catch (SocketException e) {
                System.err.println("Thers is a server on port :" + port);
            }
        }
    }
}
