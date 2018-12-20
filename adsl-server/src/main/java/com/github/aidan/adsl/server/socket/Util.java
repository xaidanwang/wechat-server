package com.github.aidan.adsl.server.socket;

import java.io.Closeable;
import java.io.IOException;

public class Util {

    public static void close(Closeable closeable){

        if ( closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
