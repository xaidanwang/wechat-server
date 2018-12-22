package com.github.aidan.adsl.server.util;

import java.io.Closeable;
import java.io.IOException;

public class StreamUtil {

    public static void closeCloseable(Closeable closeable){

        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
