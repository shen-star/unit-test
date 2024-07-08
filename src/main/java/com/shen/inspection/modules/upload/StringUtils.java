package com.shen.inspection.modules.upload;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 工具类-转换流为字节数组
 */
@Slf4j
public class StringUtils {
    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes))!= -1) {
            byteArrayOutputStream.write(bytes);
        }
        byte[] array = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return array;
    }
}
