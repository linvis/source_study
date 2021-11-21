package com.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class BioServer implements Server {
    @Override
    public void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5050);

        new Thread(() -> {
            System.out.println("Server is running...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();

                    System.out.println("Server connect to " + socket.getInetAddress());
                    new Thread(() -> {
                        int len;
                        byte[] data = new byte[1024];
                        try {
                            InputStream inputStream = socket.getInputStream();
                            OutputStream out = socket.getOutputStream();

                            // read data
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));

                                // write data
                                out.write(data, 0, len);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
