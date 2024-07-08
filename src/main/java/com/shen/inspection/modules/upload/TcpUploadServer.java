package com.shen.inspection.modules.upload;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP网络编程示例-上传文件
 */
@Slf4j
public class TcpUploadServer {
    public static void main(String[] args) throws IOException {
        //在本机监听18089端口，等待连接(无连接时阻塞)
        ServerSocket serverSocket = new ServerSocket(18099);
        log.info("正在监听 18099 端口号");
        //serverSocket可以通过accept()方法返回多个Socket
        Socket socket = serverSocket.accept();
        //通过getInputStream()方法，读取客户端写入到数据通道的数据
        InputStream inputStream = socket.getInputStream();
        byte[] fileBytes = StringUtils.streamToByteArray(inputStream);
        String filePath = System.getProperty("user.dir") + File.separator + "1.png";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileBytes);
        //通过getOutputStream()方法，将数据写入数据通道
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("收到 test 图片");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        //关闭外层流和socket
        bufferedWriter.close();
        socket.close();
        serverSocket.close();
        log.info("服务端 结束");
    }
}
