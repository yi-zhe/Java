package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E2MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Could not find this computer's address");
        }
    }
}
