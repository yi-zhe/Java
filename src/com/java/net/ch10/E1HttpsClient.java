package com.java.net.ch10;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class E1HttpsClient {

    public static void main(String[] args) {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;
        try {
            socket = (SSLSocket) factory.createSocket("www.usps.com", 443);
            socket.setEnabledCipherSuites(socket.getSupportedCipherSuites());

            Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            writer.write("GET http://www.usps.com/ HTTP/1.1\r\nHost: www.usps.com\r\n\r\n");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s;
            while (!(s = reader.readLine()).equals("")) {
                System.out.println(s);
            }
            System.out.println();

            String contentLength = reader.readLine();
            int length = Integer.MAX_VALUE;
            try {
                length = Integer.parseInt(contentLength.trim(), 16);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(contentLength);

            for (int i = 0, c = reader.read(); i < length && c != -1; i++, c = reader.read()) {
                System.out.write(c);
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
