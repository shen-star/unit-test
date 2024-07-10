package com.shen.inspection.modules.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP网络编程 接收端-发送端
 */
@Slf4j
public class UdpSender {
    public static void main(String[] args) throws IOException {
        //创建一个 DatagramSocket 对象，准备接收18088端口的数据
        DatagramSocket datagramSocket = new DatagramSocket(18088);
        //构建一个DatagramPacket对象，用于发送数据
        byte[] bytes = "hello receiver".getBytes();
        DatagramPacket sendDatagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 18099);
        //调用发送数据方法，输出到DatagramPacket对象中，如果没有接收到则阻塞
        datagramSocket.send(sendDatagramPacket);
        //对接受到数据的DatagramPacket对象进行解包
        //获取实际数据包的长度和数据
        byte[] receiveBytes = new byte[1024];
        DatagramPacket receiveDatagramPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
        datagramSocket.receive(receiveDatagramPacket);
        int packageLength = receiveDatagramPacket.getLength();
        byte[] packageData = receiveDatagramPacket.getData();
        log.info("发送端收到的数据为: {}", new String(packageData, 0, packageLength));

        //关闭资源
        datagramSocket.close();
        log.info("Sender 结束");
    }
}
