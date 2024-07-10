package com.shen.inspection.modules.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP网络编程 发送端-接收端
 */
@Slf4j
public class UdpReceiver {
    public static void main(String[] args) throws IOException {
        //创建一个 DatagramSocket 对象，准备接收18099端口的数据
        DatagramSocket datagramSocket = new DatagramSocket(18099);
        //构建一个DatagramPacket对象，用于接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket receiveDatagramPacket = new DatagramPacket(bytes, bytes.length);
        //调用接受数据方法，输出到DatagramPacket对象中，如果没有接收到则阻塞
        datagramSocket.receive(receiveDatagramPacket);
        //对接受到数据的DatagramPacket对象进行解包
        //获取实际数据包的长度和数据
        int packageLength = receiveDatagramPacket.getLength();
        byte[] packageData = receiveDatagramPacket.getData();
        log.info("接收端收到的数据为: {}", new String(packageData, 0, packageLength));
        byte[] sendBytes = "I'm fine, hello sender".getBytes();
        DatagramPacket sendDatagramPacket = new DatagramPacket(sendBytes, sendBytes.length, InetAddress.getLocalHost(), 18088);
        //调用发送数据方法，输出到DatagramPacket对象中，如果没有接收到则阻塞
        datagramSocket.send(sendDatagramPacket);
        //关闭资源
        datagramSocket.close();
        log.info("Receiver 结束");
    }
}
