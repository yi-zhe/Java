package com.java.net.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E4PooledDaytimeServer {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(8090)) {
            while (true) {
                try {
                    Socket socket = server.accept();
                    Callable<Void> daytimeCallable = new DaytimeCallable(socket);
                    service.submit(daytimeCallable);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DaytimeCallable implements Callable<Void> {

        private Socket socket;

        private DaytimeCallable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public Void call() throws Exception {
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(new Date().toString() + "\r\n");
            writer.flush();
            writer.close();
            return null;
        }
    }
}
