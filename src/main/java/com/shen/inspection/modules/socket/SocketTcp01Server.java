package com.shen.inspection.modules.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP网络编程示例-服务端
 */
@Slf4j
public class SocketTcp01Server {
    public static void main(String[] args) throws IOException {
        //在本机监听18089端口，等待连接(无连接时阻塞)
        ServerSocket serverSocket = new ServerSocket(18099);
        log.info("正在监听 18099 端口号");
        //serverSocket可以通过accept()方法返回多个Socket
        Socket socket = serverSocket.accept();
        //通过getInputStream()方法，读取客户端写入到数据通道的数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            log.info(new String(bytes, 0, len));
        }
        //通过getOutputStream()方法，将数据写入数据通道
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello client".getBytes());
        //写入后设置结束标志
        socket.shutdownOutput();
        //关闭流和socket
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        log.info("服务端 结束");
    }
}
