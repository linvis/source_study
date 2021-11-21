package com.project;

public class Main {
    public static void main(String[] args) throws Exception {
//        BioServer server = new BioServer();
//        server.start();

//        NioServer nioServer = new NioServer();
//        nioServer.start();

        NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }
}
