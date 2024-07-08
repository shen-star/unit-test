package com.shen.inspection.modules.upload;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TCP网络编程示例-上传文件
 */
@Slf4j
public class TcpUploadClient {
    public static void main(String[] args) throws IOException {
        //连接服务端(ip,端口号)，连接上服务端后生成socket
        Socket socket = new Socket(InetAddress.getLocalHost(),18099);
        //通过输出流，写入数据到数据通道
        OutputStream outputStream = socket.getOutputStream();
        File file = new File("E:\\test.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] fileBytes = StringUtils.streamToByteArray(fileInputStream);
        outputStream.write(fileBytes);
        socket.shutdownOutput();
        //通过getInputStream()方法，读取服务端写入到数据通道的数据
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        log.info("{}", s);
        //关闭外层流和socket
        bufferedReader.close();
        fileInputStream.close();
        outputStream.close();
        socket.close();
        log.info("客户端 结束");
    }
}
