package com.java.net.ch9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class E11Redirector {

    private final int port;
    private final String newSite;

    public static void main(String[] args) {
        E11Redirector redirector = new E11Redirector(8090, "https://www.baidu.com");
        redirector.start();
    }

    public E11Redirector(int port, String newSite) {
        this.port = port;
        this.newSite = newSite;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket socket = server.accept();
                    Thread thread = new RedirectThread(socket);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class RedirectThread extends Thread {
        private final Socket connection;

        private RedirectThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "US-ASCII"));
                Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) break;
                    request.append((char) c);
                }

                String get = request.toString();
                String[] pieces = get.split("\\w*");
                String theFile = pieces[1];

                if (get.contains("HTTP")) {
                    out.write("HTTP/1.0 302 FOUND\r\n");
                    Date now = new Date();
                    out.write("Date: " + now + "\r\n");
                    out.write("Server: Redirector 1.1\r\n");
                    out.write("Location: " + newSite + theFile + "\r\n");
                    out.write("Content-type: text/html\r\n\r\n");
                    out.flush();
                }

                out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                out.write("<BODY><H1>Document moved</H1>\r\n");
                out.write("The document " + theFile
                        + "has moved to \r\n<A HREF=\"" + newSite + theFile + "\">"
                        + newSite + theFile + "</A>.\r\n Please update your bookmarks<P>");
                out.write("</BODY></HTML>\r\n");
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
