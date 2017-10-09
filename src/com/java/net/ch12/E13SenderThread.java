package com.java.net.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class E13SenderThread extends Thread {

    private InetAddress server;
    private DatagramSocket socket;
    private int port;
    private volatile boolean stopped;

    E13SenderThread(DatagramSocket socket, InetAddress address, int port) {
        this.server = address;
        this.port = port;
        this.socket = socket;
        this.socket.connect(server, port);
    }

    public void halt() {
        this.stopped = true;
    }

    @Override
    public void run() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (stopped) return;

                String theLine = userInput.readLine();
                if (".".equals(theLine)) break;

                byte[] data = theLine.getBytes("UTF-8");
                DatagramPacket outgoing = new DatagramPacket(data, data.length, server, port);
                socket.send(outgoing);
                Thread.yield();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
