package com.java.net.ch12;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class E17UDPEchoClientWithChannels {
    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("localhost", 8090));

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n = 0;
            int numbersRead = 0;
            while (true) {
                if (numbersRead == 100) break;

                selector.select(6000);
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                if (readyKeys.isEmpty() && n == 100) {
                    break;
                } else {
                    Iterator<SelectionKey> iterator = readyKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isReadable()) {
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read:" + echo);
                            numbersRead++;
                        }

                        if (key.isWritable()) {
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote:" + n);
                            n++;
                            if (n == 100) {
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }

            }

            System.out.println("Echoed " + numbersRead + " out of " + 100 + " sent");
            System.out.println("Success rate:" + 100.0 * numbersRead / 100 + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
