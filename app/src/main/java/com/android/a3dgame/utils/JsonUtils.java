package com.android.a3dgame.utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class JsonUtils {
    public static List<Game> getData(String jsonStr){
        List<Game> list=new ArrayList<>();
        try {
            JSONObject obj=new JSONObject(jsonStr);
            JSONObject obj1=obj.getJSONObject("data");
            for (int i = 0; i <obj1.length() ; i++) {
                JSONObject obj2=obj1.getJSONObject(""+i);
                Game g=new Game();
                g.setId(obj2.getString("id"));
                g.setTitle(obj2.getString("title"));
                g.setSenddate(obj2.getString("senddate"));
                g.setArcurl(obj2.getString("arcurl"));
                g.setLitpic("http://www.3dmgame.com/"+obj2.getString("litpic"));
                list.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
