package com.java.net.ch12;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class E3DatagramExample {

    public static void main(String[] args) {

        String s = "This is a test";

        try {
            byte[] data = s.getBytes("UTF-8");
            DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8090);
            System.out.println("This packet is addressed to " + dp.getAddress() + " on port " + dp.getPort());
            System.out.println("There are " + dp.getLength() + " bytes of data in the packet");
            System.out.println(new String(dp.getData(), dp.getOffset(), dp.getLength(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
