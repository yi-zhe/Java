package com.java.net.ch4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class E9SpamCheck {

    public static void main(String[] args) {
        System.out.println(isSpammer("207.34.56.23"));
    }

    private static boolean isSpammer(String ip) {
        try {
            InetAddress ia = InetAddress.getByName(ip+".sbl.spamhaus.org");
            System.out.println(ia);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
