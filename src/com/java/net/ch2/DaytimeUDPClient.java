package com.java.net.ch2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DaytimeUDPClient {

    public static void main(String[] args) {

        try (DatagramSocket datagramSocket = new DatagramSocket(0)) {
            datagramSocket.setSoTimeout(5000);

            InetAddress host = InetAddress.getLocalHost();
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, 8090);
            datagramSocket.send(request);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            datagramSocket.receive(response);

            System.out.println(new String(response.getData(), 0, response.getLength(), "US-ASCII"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
