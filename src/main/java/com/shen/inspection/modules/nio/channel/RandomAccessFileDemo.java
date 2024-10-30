package com.shen.inspection.modules.nio.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * RandomAccessFile类常用方法使用
 */
@Slf4j
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        //文件的绝对路径
        String filePath = "E:\\test\\demo.txt";
        try {
            //内容写入文件
            writeToFIle(filePath, "second insert: other test");
            //文件内容读取
            String fileContent = readFromFile(filePath);
            log.info("文件内容为:{}", fileContent);
        } catch (Exception e) {
            log.warn("RandomAccessFile操作文件异常:", e);
        }

    }

    public static void writeToFIle(String filePath, String content) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            //在文件末尾写入数据
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(content.getBytes(StandardCharsets.UTF_8));
        }

    }

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            //在文件头开始读入
            randomAccessFile.seek(0);
            byte[] bytes = new byte[5];
            int byteRead;
            while ((byteRead = randomAccessFile.read(bytes)) != -1) {
                log.info("读取了{}个字节", byteRead);
                String content = new String(bytes, 0, byteRead);
                log.info("读取的内容为:{}", content);
                stringBuilder.append(content);
            }
        }
        return stringBuilder.toString();
    }
}
