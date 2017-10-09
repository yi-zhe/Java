package com.java.net.ch12;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class E12UDPEchoClient {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            E13SenderThread sender = new E13SenderThread(socket, ia, 8090);
            sender.start();
            E14ReceiverThread receiver = new E14ReceiverThread(socket);
            receiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
