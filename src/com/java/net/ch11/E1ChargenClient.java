package com.java.net.ch11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class E1ChargenClient {

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1", 8090);
            SocketChannel client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);
            while (client.read(buffer) != -1) {
                System.out.println(buffer.position());
                buffer.flip();
                System.out.println(buffer.position());
                out.write(buffer);
                buffer.clear();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
