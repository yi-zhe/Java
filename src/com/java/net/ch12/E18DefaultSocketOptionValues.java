package com.java.net.ch12;

import java.net.SocketOption;
import java.nio.channels.DatagramChannel;

public class E18DefaultSocketOptionValues {

    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            for (SocketOption<?> option : channel.supportedOptions()) {
                System.out.println(option.name() + ":" + channel.getOption(option));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
