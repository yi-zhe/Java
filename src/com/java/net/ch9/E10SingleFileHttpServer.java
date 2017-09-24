package com.java.net.ch9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class E10SingleFileHttpServer {

    public static void main(String[] args) {
        int port = 8090;
        String encoding = "UTF-8";
        String path = "d:/hello.txt";
        try {
            byte[] data = Files.readAllBytes(Paths.get(path));
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(path);
            E10SingleFileHttpServer server = new E10SingleFileHttpServer(data, encoding, contentType, port);
            server.start();
        } catch (ArithmeticException | IOException e) {
            e.printStackTrace();
        }
    }


    private static final Logger logger = Logger.getLogger("SingleFileHttpServer");

    private final byte[] content;
    private final byte[] header;
    private final int port;
    private final String encoding;

    public E10SingleFileHttpServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    private E10SingleFileHttpServer(byte[] data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n"
                + "Server: OneFile 2.0\r\n"
                + "Content-length: " + mimeType + "; charset=" + encoding + "\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connection on port " + server.getLocalPort());
            logger.info("Data to be sent");
            logger.info(new String(content, encoding));

            while (true) {
                try {
                    Socket connection = server.accept();
                    pool.submit(new HTTPHandler(connection));
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception accepting connection", e);
                } catch (RuntimeException e) {
                    logger.log(Level.SEVERE, "Unexpected error", e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server", e);
        }
    }

    private class HTTPHandler implements Callable<Void> {

        private final Socket connection;

        private HTTPHandler(Socket c) {
            connection = c;
        }

        @Override
        public Void call() throws IOException {
            try {
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                InputStream in = new BufferedInputStream(connection.getInputStream());
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) break;
                    request.append((char) c);
                }

                if (request.toString().contains("HTTP/")) {
                    out.write(header);
                }

                out.write(content);
                out.flush();

            } catch (IOException e) {
                logger.log(Level.WARNING, "Error write to client", e);
            } finally {
                if (connection != null)
                    connection.close();
            }
            return null;
        }
    }
}
