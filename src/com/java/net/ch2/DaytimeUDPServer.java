package com.java.net.ch2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class DaytimeUDPServer {

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(8090)) {
            while (true) {
                DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                socket.receive(request);

                String dayTime = new Date().toString();
                byte[] data = dayTime.getBytes("US-ASCII");
                DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
