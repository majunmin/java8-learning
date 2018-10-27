package com.mjm.lambda;

import com.mjm.lambda.function.BufferReaderProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author majun
 * @date 2018/10/25 15:42
 *
 * 消除冗余代码
 *
 */
public class AroundExecute {


    public static void main(String[] args) throws IOException {
        String content = processFile((BufferedReader br) -> br.readLine() +br.readLine());
        System.out.println(content);
    }


    /**
     * 冗余代码太多
     * @return
     * @throws IOException
     */
    private static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("D://a.txt"))){
            return br.readLine();
        }
    }

    /**
     * 使用函数式接口 简化代码
     * @param p
     * @return
     * @throws IOException
     */
    private static String processFile(BufferReaderProcess p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("D://a.txt"))){
            return p.processFile(br);
        }
    }

}
