package com.shen.inspection.modules.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TCP网络编程示例-服务端
 */
@Slf4j
public class SocketTcp01Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),18099);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello server".getBytes());
        outputStream.close();
        socket.close();
        log.info("客户端 结束");
    }
}
