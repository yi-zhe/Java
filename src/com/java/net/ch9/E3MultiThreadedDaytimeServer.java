package com.java.net.ch9;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class E3MultiThreadedDaytimeServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8090)) {
            while (true) {
                try {
                    Socket socket = server.accept();
                    Thread daytimeThread = new DaytimeThread(socket);
                    daytimeThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class DaytimeThread extends Thread {

        private Socket socket;

        private DaytimeThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try  {
                OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
                writer.write(new Date().toString() + "\r\n");
                writer.flush();
                writer.close();
                System.out.println("Thread id=" + getId());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(socket !=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
