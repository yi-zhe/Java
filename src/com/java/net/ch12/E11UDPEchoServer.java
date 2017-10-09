package com.java.net.ch12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class E11UDPEchoServer extends E9UDPServer {

    private E11UDPEchoServer() {
        super(8090);
    }

    @Override
    protected void respond(DatagramSocket socket, DatagramPacket incoming) throws IOException {
        DatagramPacket outgoing = new DatagramPacket(incoming.getData(), incoming.getLength(), incoming.getAddress(), incoming.getPort());
        socket.send(outgoing);
    }

    public static void main(String[] args) {
        E11UDPEchoServer server = new E11UDPEchoServer();
        Thread t = new Thread(server);
        t.start();
    }
}
