package com.shen.inspection.modules.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TCP网络编程示例-客户端
 */
@Slf4j
public class SocketTcp01Client {
    public static void main(String[] args) throws IOException {
        //连接服务端(ip,端口号)，连接上服务端后生成socket
        Socket socket = new Socket(InetAddress.getLocalHost(),18099);
        //通过输出流，写入数据到数据通道
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello server".getBytes());
        //设置结束标志
        socket.shutdownOutput();
        //通过getInputStream()方法，读取服务端写入到数据通道的数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            log.info(new String(bytes, 0, len));
        }
        //关闭流和socket
        inputStream.close();
        outputStream.close();
        socket.close();
        log.info("客户端 结束");
    }
}
