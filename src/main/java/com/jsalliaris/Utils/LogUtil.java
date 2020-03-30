package com.jsalliaris.Utils;

public class LogUtil {

    private static boolean debug;

    public static void log(String text){
        if(debug){
            System.out.println(text);
        }
    }

    public static void logError(String text){
        if(debug){
            System.err.println(text);
        }
    }

    public static void setDebug(boolean debug) {
        LogUtil.debug = debug;
    }
}
