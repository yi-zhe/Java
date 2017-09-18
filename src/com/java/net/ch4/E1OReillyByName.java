package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E1OReillyByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.oreilly.com");
            System.out.println(address);

            InetAddress address2 = InetAddress.getByName("23.218.2.90");
            System.out.println(address2.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Could not find www.oreilly.com");
        }
    }
}
