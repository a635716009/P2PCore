package com.bw.example;


import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/20.
 */

public class DataUtil {
    private static JSONObject devToJSON(Dev dev)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("DID", dev.getDID());
            jsonObject.put("username", dev.getUserName());
            jsonObject.put("password", dev.getPassword());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
    private static Dev jsonToDev(JSONObject jsonObject)
    {
        Dev dev=null;
        try {
            dev=new Dev(jsonObject.getString("DID"),jsonObject.getString("username"),jsonObject.getString("password"));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return dev;
    }

    static String listToString(ArrayMap<String,Dev> list)
    {
        JSONArray jsonArray=new JSONArray();
        for(int i=0;i<list.size();i++){
            jsonArray.put(devToJSON(list.valueAt(i)));
        }
        return jsonArray.toString();
    }

    static ArrayMap<String,Dev> stringToList(String s)
    {
        ArrayMap<String,Dev> list= new ArrayMap<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0;i<jsonArray.length();i++)
            {
                Dev dev = jsonToDev(jsonArray.getJSONObject(i));
                list.put(dev.getDID(),dev);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
