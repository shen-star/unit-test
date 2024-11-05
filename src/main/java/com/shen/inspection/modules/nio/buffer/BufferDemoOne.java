package com.shen.inspection.modules.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * byte缓冲区的使用
 */
@Slf4j
public class BufferDemoOne {
    public static void main(String[] args) throws IOException {
        //新增一个随机访问文件类，用于生成文件通道
        RandomAccessFile file = new RandomAccessFile("E:\\test\\demo.txt", "rw");
        //生成文件通道，文件通道是双向的，可读可写
        FileChannel fileChannel = file.getChannel();
        //创建一个字节缓冲区，容量为24字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(24);
        //新增一个int变量，用于统计从文件通道读取到的字节个数
        int readLen;
        //遍历读取文件内容
        while ((readLen = fileChannel.read(byteBuffer)) != -1) {
            log.info("读取到了[{}]个字节", readLen);
            //缓冲区从写模式切换为读模式
            byteBuffer.flip();
            //遍历读取缓存区的数据
            while (byteBuffer.hasRemaining()) {
                //读取一个字节的数据，缓存区指针后移一位
                log.info("从缓冲区读取到:[{}]", (char) byteBuffer.get());
            }
            //清空缓冲区，位置置为0
            byteBuffer.clear();
        }
        //关闭文件通道
        fileChannel.close();
        //关闭随机访问文件类
        file.close();
    }
}
