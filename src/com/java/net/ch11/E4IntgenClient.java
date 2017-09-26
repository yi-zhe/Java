package com.java.net.ch11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

public class E4IntgenClient {

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress(8090));
        ByteBuffer buffer = ByteBuffer.allocate(4);
        IntBuffer view = buffer.asIntBuffer();

        for (int expected = 0; ; expected++) {
            client.read(buffer);
            int actual = view.get();
            buffer.clear();
            view.rewind();
            if (actual != expected) {
                System.err.println("Expected " + expected + "; was " + actual);
                break;
            }
        }
    }

}
