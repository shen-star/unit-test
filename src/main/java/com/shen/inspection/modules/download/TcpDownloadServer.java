package com.shen.inspection.modules.download;

import com.shen.inspection.modules.upload.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP网络编程文件下载
 */
@Slf4j
public class TcpDownloadServer {
    public static void main(String[] args) throws IOException {
        //在本机监听18089端口，等待连接(无连接时阻塞)
        ServerSocket serverSocket = new ServerSocket(18099);
        log.info("正在监听 18099 端口号");
        //serverSocket可以通过accept()方法返回多个Socket
        Socket socket = serverSocket.accept();
        //通过输入流，接收数据通道数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder fileName = new StringBuilder();
        //获取文件名数据
        while ((len = inputStream.read(bytes)) != -1) {
            fileName.append(new String(bytes, 0, len));
        }
        log.info("客户端希望下载的文件名为:{}", fileName);
        String filePath = "E:" + File.separator + fileName;
        File file = new File(filePath);
        FileInputStream fileInputStream;
        //判断传输过滤的文件路径是否真实存在，如果不存在则传输一个默认文件
        if (file.exists()) {
            fileInputStream = new FileInputStream(filePath);
        } else {
            fileInputStream = new FileInputStream("E:\\test.png");
        }
        byte[] fileBytes = StringUtils.streamToByteArray(fileInputStream);
        //通过输出流，写入数据到数据通道
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(fileBytes);
        socket.shutdownOutput();
        //关闭外层流和socket
        outputStream.close();
        fileInputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        log.info("服务端 结束");
    }
}
