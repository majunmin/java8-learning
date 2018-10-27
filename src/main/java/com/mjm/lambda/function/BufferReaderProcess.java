package com.mjm.lambda.function;

import jdk.nashorn.internal.objects.annotations.Function;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author majun
 * @date 2018/10/25 15:47
 */
@FunctionalInterface
public interface BufferReaderProcess {

    String processFile(BufferedReader br) throws IOException;
}
