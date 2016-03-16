package com.peach.dec.util.constant;

import java.io.IOException;

public class RestoreOralce {

    public static boolean restoreOralce(String file) {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd /c sqlplus scott/tiger @DEC" );
            String cmd = "imp scott/tiger@DEC full=y file=d:\\"+file+".dmp ignore=y"; // 一定要加 -hlocalhost(或是服务器IP地址)
            rt.exec("cmd /c " + cmd);
            System.out.println("还原成功!");
            return true;
        } catch (IOException e) {
            System.out.println("还原失败!");
            e.printStackTrace();
            return false;
        }

    }
    public static void main(String[] args) {
 	   RestoreOralce.restoreOralce("daochu");
 } 
}
