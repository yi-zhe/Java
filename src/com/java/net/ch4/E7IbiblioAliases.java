package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E7IbiblioAliases {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
        InetAddress helios = InetAddress.getByName("helios.ibiblio.org");

        if (ibiblio.equals(helios)) {
            System.out.println("they are the same");
        } else {
            System.out.println("they are different");
        }
    }
}
