package com.java.net.ch12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class E9UDPServer implements Runnable {

    private final int bufferSize;
    private final int port;
    private volatile boolean isShutdown = false;

    public E9UDPServer(int port, int bufferSize) {
        this.bufferSize = bufferSize;
        this.port = port;
    }

    public E9UDPServer(int port) {
        this(port, 8192);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[bufferSize];
        try (DatagramSocket socket = new DatagramSocket(port)) {
            socket.setSoTimeout(10000);
            while (true) {
                if (isShutdown) return;
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(incoming);
                    respond(socket, incoming);
                } catch (IOException e) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void respond(DatagramSocket socket, DatagramPacket incoming);
}
