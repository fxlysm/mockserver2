package cn.imovie.mockserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/7/20.
 */
public class MapJson {
    public static Map Json2Map(String jsonStrings){
        Map<String, String> map = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(jsonStrings, new TypeReference<HashMap<String,String>>(){});
//            System.out.println(map); System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String  Map2Json(Map map){
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(map);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

         return json;
    }

    public void Map2JsonFile(Map map){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("F:/user.json"), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void JsonFile2Map(String path){
        //path="F:/user.json"
        try{
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(new File(path),new TypeReference<Map<String, Object>>(){});

            System.out.println("name: " + map.get("name"));
            System.out.println("age: " + map.get("age"));

            @SuppressWarnings("unchecked")
            List<String> list = (List<String>)map.get("hobby");
            System.out.print("hobby: ");
            for(String str : list){
                System.out.print(str+ " ");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = "";

            Map<String, String> map = new HashMap<String,String>();
            map.put("name", "liuyong");
            map.put("age", "30");

            json = mapper.writeValueAsString(map);
            System.out.println(map);
            System.out.println(json);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
