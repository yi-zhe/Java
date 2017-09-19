package com.java.net.ch5;

import java.net.MalformedURLException;
import java.net.URL;

public class E1ProtocolTester {

    public static void main(String[] args) {
        testProtocol("http://www.baidu.com");
        testProtocol("https://www.baidu.com");
        testProtocol("ftp://ibiblio.org/faq.pdf");
        testProtocol("mailto:a@ibiblio.org");
        testProtocol("telnet://dibner.poly.edu");
        testProtocol("file:///etc/passwd");
        testProtocol("gopher://gopher.anc.org.za/");
        testProtocol("ldap://ldap.itd.umich.edu/o=University");
        testProtocol("jar://http://cafeaulait.org/books/io/StreamCopier.class");
        testProtocol("nfs://utopia.poly.edu/usr/tmp/");
        testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");
        testProtocol("rmi://ibiblio.org/RenderEngine");
        testProtocol("doc:/UserGuide/release.html");
        testProtocol("netdoc:/UserGuide/release.html");
        testProtocol("verbatim:http://www.adc.org/");
    }

    private static void testProtocol(String url) {
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(":"));
            System.out.println(protocol + " is  not supported");
        }
    }
}
