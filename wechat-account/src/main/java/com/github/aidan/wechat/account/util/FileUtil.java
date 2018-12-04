package com.github.aidan.wechat.account.util;

public class FileUtil {

    public static String getFileName(Integer status){

        String fileName ="test";
        switch (status){
            case 1:
                fileName ="正常号";
                break;
            case 2:
                fileName ="封号";
                break;
            case 3:
                fileName ="解封号";
                break;
            case 4:
                fileName ="未知号";
                break;
            default:
                return null;
        }
        return fileName;
    }
}
