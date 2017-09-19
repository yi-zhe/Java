package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通配地址
 * ipv4: 0.0.0.0
 * ipv6: 0:0:0:0:0:0:0:0或者::
 * <p>
 * 回送地址
 * 127.0.0.1
 * 0:0:0:0:0:0:0:1或者::1
 */
public class E4MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostAddress());
            byte[] addr = address.getAddress();
            System.out.println(ip(addr));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Could not find this computer's address");
        }
    }

    private static String ip(byte[] addr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < addr.length; i++) {
            int unsignedByte = addr[i] < 0 ? addr[i] + 256 : addr[i];
            builder.append(unsignedByte);
            if (i != addr.length - 1) {
                builder.append(".");
            }
        }
        return builder.toString();
    }
}
