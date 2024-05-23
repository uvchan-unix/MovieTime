package com.zs.movietime.models.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zs.movietime.models.DAO.pojo.*;

public class JsonUtils {

    public static JSONObject objToJson(Object obj) {

        JSONObject jsonObject = new JSONObject();

        for (Field field : obj.getClass().getDeclaredFields()) {
            // System.out.println(obj.getClass().getDeclaredFields());
            try {
                field.setAccessible(true);
                jsonObject.put(field.getName(), field.get(obj));
                // System.out.println(field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return jsonObject;

    }

    public static JSONArray resultsetToJson(ResultSet resultset) {

        try {
            if (resultset != null) {
                JSONArray jsonArray = new JSONArray();
                
                ResultSetMetaData resultMeta = resultset.getMetaData();
                int columnCount = resultMeta.getColumnCount();
                
                while (resultset.next()) {
                    
                    JSONObject json = new JSONObject();

                    for (int i = 1; i <= columnCount; i++) {
                        
                        String columnName = resultMeta.getColumnName(i);
                        Object obj = resultset.getObject(i);

                        json.put(columnName, obj);

                    }

                    jsonArray.put(json);

                }
                return jsonArray;
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

        return new JSONArray("[{'Error' : 'Cannot convert into json'}]");

    }

    public static void main(String[] args) {

        Movie movie = new Movie("data", "http://test:6060", "{summa}");
        // JsonUtils j = new JsonUtils();
        System.out.println(objToJson(movie));

    }

}
