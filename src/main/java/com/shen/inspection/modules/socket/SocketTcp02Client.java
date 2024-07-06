package com.shen.inspection.modules.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * TCP网络编程示例-客户端
 */
@Slf4j
public class SocketTcp02Client {
    public static void main(String[] args) throws IOException {
        //连接服务端(ip,端口号)，连接上服务端后生成socket
        Socket socket = new Socket(InetAddress.getLocalHost(),18099);
        //通过输出流，写入数据到数据通道
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello server 字符流");
        //插入换行符，对应输入流需要调用readLine方法
        bufferedWriter.newLine();
        //完成写入后需要flush
        bufferedWriter.flush();
        //通过getInputStream()方法，读取服务端写入到数据通道的数据
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        log.info("{}", s);
        //关闭外层流和socket
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        log.info("客户端 结束");
    }
}
