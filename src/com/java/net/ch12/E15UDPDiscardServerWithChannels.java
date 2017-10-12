package com.java.net.ch12;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class E15UDPDiscardServerWithChannels {

    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(8090));
            ByteBuffer buffer = ByteBuffer.allocateDirect(65507);
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                System.out.println(client + " says ");
                while (buffer.hasRemaining()) {
                    System.out.write(buffer.get());
                }
                System.out.println();
                buffer.clear();
            }
        } catch (Exception e) {

        }
    }

}
