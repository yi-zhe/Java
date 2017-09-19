package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E3ReverseTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getByName("oreilly.com");
        System.out.println(ia.getAddress()[2]);
    }
}
