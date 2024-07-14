package com.shen.inspection.modules.download;

import com.shen.inspection.modules.upload.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP网络编程-文件下载
 */
@Slf4j
public class TcpDownloadClient {
    public static void main(String[] args) throws IOException {
        //连接服务端(ip,端口号)，连接上服务端后生成socket
        Socket socket = new Socket(InetAddress.getLocalHost(), 18099);
        //通过输出流，写入数据到数据通道
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        log.info("请输入需要下载的文件名:");
        String fileName = scanner.next();
        outputStream.write(fileName.getBytes());
        socket.shutdownOutput();
        //通过输入流，接收数据通道数据
        InputStream inputStream = socket.getInputStream();
        byte[] fileBytes = StringUtils.streamToByteArray(inputStream);
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileBytes);
        //关闭外层流和socket
        fileOutputStream.close();
        inputStream.close();
        outputStream.close();
        socket.close();
        log.info("客户端 结束");
    }
}
