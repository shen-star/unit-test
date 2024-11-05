package com.shen.inspection.modules.nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * int缓冲区的使用
 */
@Slf4j
public class BufferDemoTwo {
    public static void main(String[] args) {
        //新增容器为8的int缓冲区
        IntBuffer intBuffer = IntBuffer.allocate(8);
        //向int缓冲区写入数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i+1);
        }
        //int缓冲区切换成读模式
        intBuffer.flip();
        //遍历读取缓冲区数据
        while (intBuffer.hasRemaining()) {
            int num = intBuffer.get();
            log.info("读取到:[{}]", num);
        }
        //清空缓存，位置指针置为0，之前的数据还在
        intBuffer.clear();
        log.info("清空缓存后，是否还有数据:{}", intBuffer.hasRemaining());
    }
}
