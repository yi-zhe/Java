package com.java.net.ch5;

import java.net.MalformedURLException;
import java.net.URL;

public class E4URLSplitter {

    public static void main(String[] args) throws MalformedURLException {
//        String url = "ftp://mp3:mp3@138.247.121.61:21000/c%3a/";
//        String url = "http://www.oreilly.com";
//        String url = "http://www.ibiblio.org/nywc/compositions.phtml?category=Piano";
        String url = "http://admin@www.blackstar.com:8080/";
        URL u = new URL(url);
        System.out.println("The url is " + u);
        System.out.println("The scheme is " + u.getProtocol());
        System.out.println("The user info is " + u.getUserInfo());

        String host = u.getHost();
        if (host != null) {
            int atSign = host.indexOf('@');
            if (atSign != -1) host = host.substring(atSign + 1);
            System.out.println("The host is " + host);
        } else {
            System.out.println("The host is null.");
        }

        System.out.println("The port is " + u.getPort());
        System.out.println("The path is " + u.getPath());
        System.out.println("The ref is " + u.getRef());
        System.out.println("The query string is " + u.getQuery());
    }

}
