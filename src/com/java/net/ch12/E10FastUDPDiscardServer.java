package com.java.net.ch12;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class E10FastUDPDiscardServer extends E9UDPServer {

    private E10FastUDPDiscardServer() {
        super(8090);
    }

    public static void main(String[] args) {
        E10FastUDPDiscardServer server = new E10FastUDPDiscardServer();
        Thread t = new Thread(server);
        t.start();
    }

    @Override
    protected void respond(DatagramSocket socket, DatagramPacket incoming) {

    }
}
