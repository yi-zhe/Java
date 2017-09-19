package com.java.net.ch4;

import java.net.InetAddress;

public class E6IPCharateristics {

    public static void main(String[] args) {
//        String ip = "192.168.254.32";
//        String ip = "127.0.0.1";
//        String ip = "www.baidu.com";
//        String ip = "224.0.2.1";
//        String ip = "FF01:0:0:0:0:0:0:1";
//        String ip = "FF05:0:0:0:0:0:0:101";
        String ip = "0::1";
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isAnyLocalAddress()) {
                System.out.println(address + " is a wildcard address.");
            }
            if (address.isLoopbackAddress()) {
                System.out.println(address + " is a loopback address.");
            }

            if (address.isLinkLocalAddress()) {
                System.out.println(address + " is a link-local address.");
            } else if (address.isSiteLocalAddress()) {
                System.out.println(address + " is a site-local address.");
            } else {
                System.out.println(address + " is a global address.");
            }

            if (address.isMulticastAddress()) {
                if (address.isMCGlobal()) {
                    System.out.println(address + " is a global multi-cast address.");
                } else if (address.isMCOrgLocal()) {
                    System.out.println(address + " is an organization wide multi-cast address.");
                } else if (address.isMCSiteLocal()) {
                    System.out.println(address + " is a site wide address.");
                } else if (address.isMCLinkLocal()) {
                    System.out.println(address + " is a subnet wide multi-cast address.");
                } else if (address.isMCNodeLocal()) {
                    System.out.println(address + " is an interface-local multi-cast address.");
                } else {
                    System.out.println(address + " is an unknown multi-cast address.");
                }
            } else {
                System.out.println(address + " is a uni-cast address.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
