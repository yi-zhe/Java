package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E5AddressType {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(getVersion(InetAddress.getLocalHost()));
    }

    private static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        return -1;
    }
}

