package com.shen.inspection.modules.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP网络编程示例-客户端
 */
@Slf4j
public class SocketTcp01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(18099);
        log.info("正在监听 18099 端口号");
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            log.info(new String(bytes, 0, len));
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
        log.info("服务端 结束");
    }
}
