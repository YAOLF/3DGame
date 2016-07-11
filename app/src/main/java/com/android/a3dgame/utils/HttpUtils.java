package com.android.a3dgame.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/8.
 */
public class HttpUtils {
    public static  byte[] getData(String str){
        try {
            URL url=new URL(str);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if(conn.getResponseCode()==200){
                InputStream input=conn.getInputStream();
                ByteArrayOutputStream bos=new ByteArrayOutputStream() ;
                int len=0;
                byte[] b=new byte[1024];
                while ((len=input.read(b))!=-1){
                    bos.write(b,0,len);
                }
                return bos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
