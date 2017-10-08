package com.java.net.ch2;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class E7UDPPoke {

    private int bufferSize;
    private int timeout;
    private InetAddress host;
    private int port;

    private E7UDPPoke(InetAddress host, int port) {
        this(host, port, 8192);
    }

    private E7UDPPoke(InetAddress host, int port, int bufferSize) {
        this(host, port, bufferSize, 5000);
    }

    private E7UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
        this.host = host;
        this.port = port;
        this.bufferSize = bufferSize;
        this.timeout = timeout;
    }

    private byte[] poke() {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.connect(host, port);
            socket.setSoTimeout(timeout);
            socket.send(request);

            DatagramPacket response = new DatagramPacket(new byte[bufferSize], bufferSize);
            socket.receive(response);

            int bytes = response.getLength();
            byte[] data = new byte[bytes];
            System.arraycopy(response.getData(), 0, data, 0, bytes);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        InetAddress host;
        int port = 8090;
        try {
            host = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }

        try {
            E7UDPPoke poker = new E7UDPPoke(host, port);
            byte[] response = poker.poke();
            if (response == null) {
                System.err.println("No response");
                return;
            }

            System.out.println(new String(response, "US-ASCII"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
