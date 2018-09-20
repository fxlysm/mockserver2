package cn.imovie.mockserver.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = createMapper();

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static ObjectMapper createMapper() {
        ObjectMapper result = new ObjectMapper();
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        result.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        result.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        result.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输出时包含属性的风格
        result.setSerializationInclusion(Include.NON_EMPTY);
        return result;
    }

    public static String object2String(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        String ret = "";
        try {
            ret = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ret;
    }

    public static JsonNode parseNode(String text) {
        try {
            return mapper.readTree(text);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <E> List<E> jsonToList(String str, Class<?> elementClasses) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            JavaType javaType = getCollectionType(ArrayList.class, elementClasses);
            return mapper.readValue(str, javaType);
        } catch (IOException e) {
            logger.warn("parse json string error:" + str, e);
            return null;
        }
    }

    public static String getJsonPathNode(String allStr,String path) {
        //path=path.replace(".","/");
        //avg,sum,min,max,length,size,append
        ObjectMapper mapper=new ObjectMapper();
        try{
            if (path.startsWith("$.")){
                Object node= JsonPath.read(allStr,path);
                if (node instanceof JSONArray){
                    JSONArray nodeArr=(JSONArray)node;
                    if (nodeArr.size()==1)
                        return object2String(nodeArr.get(0));
                    else if (nodeArr.size()==0)
                        return null;
                    else
                        return object2String(node);
                }else
                    return object2String(node);
            }else{// if(path.startsWith("/"))
                if (!path.startsWith("/")) path="/"+path;
                JsonNode jsonNode=mapper.readTree(allStr);
                JsonNode data=jsonNode.at(path);//	/store/book
                return getJsontext(data);
            }
        }catch(Exception e){
            logger.error("Path:"+path+" can not be read...", e);
            return null;
        }
    }
    public static long getJsonPathNodeLong(String allStr,String path) {
        //path=path.replace(".","/");
        ObjectMapper mapper=new ObjectMapper();
        try{
            if (path.startsWith("$.")){
                Object node=JsonPath.read(allStr,path);
                if (node instanceof JSONArray){
                    JSONArray nodeArr=(JSONArray)node;
                    if (nodeArr.size()==1)
                        return (Long)nodeArr.get(0);
                    else
                        return Long.parseLong(node.toString());
                }else
                    return Long.parseLong(node.toString());
            }else{// if(path.startsWith("/"))
                if (!path.startsWith("/")) path="/"+path;
                JsonNode jsonNode=mapper.readTree(allStr);
                JsonNode data=jsonNode.at(path);//	/store/book
                return getJsonlong(data);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return 0;
        }
    }
    public static String getJsonPathNodeFirst(String allStr,String path) {
        //path=path.replace(".","/");
        ObjectMapper mapper=new ObjectMapper();
        try{
            if (path.startsWith("$.")){
                Object node=JsonPath.read(allStr,path);
                if (node instanceof JSONArray){
                    JSONArray nodeArr=(JSONArray)node;
                    if (nodeArr.size()==0)
                        return null;
                    if (nodeArr.size()>=1)
                        return nodeArr.get(0).toString();
                    else
                        return node.toString();
                }else
                    return node.toString();
            }else{// if(path.startsWith("/"))
                if (!path.startsWith("/")) path="/"+path;
                JsonNode jsonNode=mapper.readTree(allStr);
                JsonNode data=jsonNode.at(path);//	/store/book
                return getJsontext(data);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    public static String writeJsonNode(String allStr,String path,String field,String value,boolean add) {
        if (path.startsWith("$."))
            return JsonPath.parse(allStr).put(path,field,value).jsonString();
        if (!StringUtils.isEmpty(path)){
            path=path.replace(".","/");
            if (!path.startsWith("/")) path="/"+path;
        }
        //ObjectMapper mapper=new ObjectMapper();
        ObjectMapper mapper=getMapper();
        try{
            JsonNode jsonNode=mapper.readTree(allStr);
            JsonNode data=null;
            if (StringUtils.isEmpty(path) || path.equals("/"))
                data=jsonNode;
            else
                data=jsonNode.at(path);//	/store/book
            if (data!=null && !(data instanceof MissingNode)){
                ((ObjectNode)data).put(field,value);
                return object2String(jsonNode);
            }else{
                if (add){
                    String cpath= StringUtils.substringAfterLast(path,"/");
                    path= StringUtils.substringBeforeLast(path,"/");
                    data=jsonNode.at(path);
                    if (data!=null && !(data instanceof MissingNode)){
                        ((ObjectNode)data).putObject(cpath).put(field,value);
                        return object2String(jsonNode);
                    }
                }
                return allStr;
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return allStr;
        }
    }
    public static String writeJsonNode(String allStr,String path,String field,String value) {
        return writeJsonNode(allStr,path,field,value,false);
    }


    public static String removeJsonNode(String allStr,String path,String field) {
        path=path.replace(".","/");
        if (!path.startsWith("/")) path="/"+path;
        ObjectMapper mapper=new ObjectMapper();
        try{
            JsonNode jsonNode=mapper.readTree(allStr);
            JsonNode data=jsonNode.at(path);//	/store/book
            if (data!=null){
                if (field!=null)
                    ((ObjectNode)data).remove(field);
                else
                    ((ObjectNode)data).removeAll();
                return object2String(jsonNode);
            }else
                return allStr;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return allStr;
        }
    }
    public static String writeJsonNode(String allStr,String path,String field,int value) {
        path=path.replace(".","/");
        if (!path.startsWith("/")) path="/"+path;
        ObjectMapper mapper=new ObjectMapper();
        try{
            JsonNode jsonNode=mapper.readTree(allStr);
            JsonNode data=jsonNode.at(path);//	/store/book
            ((ObjectNode)data).put(field,value);
            return object2String(jsonNode);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return allStr;
        }
    }
    public static String removeJsonNode(String allStr,String path) {
        //JsonPath.parse(str).delete("$..bicycle").jsonString();
        try{
            if (path.startsWith("$."))
                return JsonPath.parse(allStr).delete(path).jsonString();
            else
                return removeJsonNode(allStr,path,null);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return allStr;
        }
    }


    public static String getJsontext(JsonNode node){
        if (node==null || node instanceof MissingNode || node instanceof NullNode)
            return null;
        else if(node.isObject())
            return node.toString();
        else if(node instanceof ArrayNode){
            ArrayNode arrNode=(ArrayNode)node;
            if (arrNode.size()==1)
                return object2String(arrNode.get(0));
            else
                return object2String(node);
        }else
            return node.asText();
    }
    public static long getJsonlong(JsonNode node){
        if (node==null || node instanceof MissingNode || node instanceof NullNode)
            return 0;
        else if(node.isObject())
            return 0;
        else if(node instanceof ArrayNode){
            ArrayNode arrNode=(ArrayNode)node;
            if (arrNode.size()==1)
                return getJsonlong(arrNode.get(0));
            else
                return getJsonlong(node);
        }else
            return node.asLong();
    }

    @SuppressWarnings("rawtypes")
    public static List<String> getJsonChildList(String allStr,String path) {
        try{
            if (path.startsWith("$.")){
                //https://github.com/jayway/JsonPath
                List list=new ArrayList();;
                try{
                    list=JsonPath.read(allStr,path);//"$..list[?(@.clazz==WidgetLayer)]"
                }catch(Exception e){
                    Object obj=JsonPath.read(allStr,path);//"$..list[?(@.clazz==WidgetLayer)]"
                    list.add(obj);
                }
                List<String> listNew=new ArrayList<String>();
                for(Object obj:list){
                    listNew.add(object2String(obj));
                }
                return listNew;
            }else	{
                //http://fasterxml.github.io/jackson-core/javadoc/2.8/
                //https://github.com/FasterXML/jackson
                ObjectMapper mapper=getMapper();
                JsonNode jsonNode=mapper.readTree(allStr);
                if (path.startsWith("$")){
                    List<String> list=new ArrayList<String>();
                    list=jsonNode.findValuesAsText(path.replace("$",""));
                    return list;
                }else{
                    JsonNode data=jsonNode.at(path);//	/store/book
                    List<String> list=new ArrayList<String>();
                    if (data instanceof ArrayNode){
                        ArrayNode arrNode=(ArrayNode)data;
                        for(JsonNode node:arrNode){
                            list.add(getJsontext(node));
                        }
                        return list;
                    }else
                        return Arrays.asList(getJsontext(data));
                }
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    public static ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;////允许出现特殊字符和转义符
        return mapper;
    }
    public static Object getJsonChildList(String allStr,String path,String field,String regex) {
        Object obj;
        if (field==null)
            obj=JsonPath.read(allStr,path);
        else{
            Filter cheapFictionFilter = filter(where(field).regex(Pattern.compile(regex)));
            obj=JsonPath.parse(allStr).read(path, cheapFictionFilter);//"$..book[?].category"
        }
        return obj;
    }
    public static <T> T string2Object(String inputStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            T req = mapper.readValue(inputStr, clazz);
            return req;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }



    /**
     * 将json格式转换成map对象
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, String> jsonToMap(String jsonStr) {
        Map<String, String> objMap = null;
        if (gson != null) {
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<String, String>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }



    public static  String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }





    public static HashMap<String, Object> getJsonFromString(String jsonString) {
        HashMap<String, Object> result = new HashMap<>();
        int i = 0;
        while(i < jsonString.length() - 1) {
            int nextKeyIndex = i;
            i = jsonString.indexOf(":", i);
            if (i == -1) {
                return result;
            }
            String key = getKey(jsonString.substring(nextKeyIndex, i));
            if ("".equals(key)) {
                return result;
            }
            String valueString = getValueString(jsonString.substring(i + 1));
            Object value = getValue(valueString);
            if (value == null) {
                return result;
            }
            result.put(key, value);
            i = i + valueString.length() + 2;
        }
        return result;
    }

    public static String getKey(String keyString) {
        String key = "";
        if (keyString.endsWith("\"")) {
            key = keyString.substring(keyString.indexOf("\"") + 1, keyString.length()-1);
        }
        if(key.contains("\"")){
            key = "";
        }
//        System.out.println("key = " + key);
        return key;
    }

    public static String getValueString(String jsonValueString){
        String result = "";
        if (jsonValueString.startsWith("{")) {
            int count = 0;
            for (int i = 0; i < jsonValueString.length(); i++) {
                if ("{".equals(String.valueOf(jsonValueString.charAt(i)))) {
                    count++;
                } else if ("}".equals(String.valueOf(jsonValueString.charAt(i)))) {
                    if (count == 1) {
                        result = jsonValueString.substring(0, i + 1);
                    }
                    count--;
                }
            }
        } else if (jsonValueString.startsWith("[")){
            int count = 0;
            for (int i = 0; i < jsonValueString.length(); i++) {
                if ("[".equals(String.valueOf(jsonValueString.charAt(i)))) {
                    count++;
                } else if ("]".equals(String.valueOf(jsonValueString.charAt(i)))) {
                    if (count == 1) {
                        result = jsonValueString.substring(0, i + 1);
                    }
                    count--;
                }
            }
        } else {
            int valueEndIndex = jsonValueString.indexOf(",");
            if (valueEndIndex == -1) {
                if (jsonValueString.endsWith("}")) {
                    result = jsonValueString.substring(0, jsonValueString.length() - 1);
                } else {
                    result = jsonValueString.substring(0, jsonValueString.length());
                }
            } else {
                result = jsonValueString.substring(0, valueEndIndex);
            }
        }
//        System.out.println("ValueString = " + result);
        return result;
    }

    public static Object getValue (String jsonValueString) {
        Object result = null;
        if (jsonValueString.startsWith("{")) {
            result = getJsonFromString(jsonValueString);
        } else if (jsonValueString.startsWith("[")){
            ArrayList<Object> list = new ArrayList<Object>();
            String tmp = jsonValueString.substring(1, jsonValueString.length() -1);
            while(tmp.length() > 0) {
                String valueString = getValueString(tmp);
                Object value = getValue(valueString);
                list.add(value);
                if (tmp.length() == valueString.length()) {
                    tmp = "";
                } else {
                    tmp = tmp.substring(valueString.length() + 1, tmp.length());
                }
            }
            result = list;
        } else if (jsonValueString.startsWith("\"")) {
            result = jsonValueString.substring(1, jsonValueString.length()-1);
        } else {
            try {
                result = Integer.parseInt(jsonValueString);
            } catch(NumberFormatException e) {
                if ("true".equals(jsonValueString.toLowerCase())
                        || "false".equals(jsonValueString.toLowerCase())) {
                    result = Boolean.parseBoolean(jsonValueString);
                }
                result = jsonValueString;
            }
        }
//        System.out.println("value = " + result.toString());
        return result;
    }


    //


    /**
     * 对帐单返回数据解析 解析
     * @param resp
     * @return
     */
    public static Map<String, String> BillgetResultMap(String resp) {
        System.out.println("resp:"+resp);
        Map<String, String> resultMap = new HashMap<>();

        JSONObject json = JSONObject.parseObject(resp);

        Set<String> keys = json.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = String.valueOf(json.get(key));
//			System.out.println(key + "=" + value);
            if ("statementList".equals(key)) {

                JSONArray recordArray = json.getJSONArray("statementList");
                if (recordArray.size() > 0) {
                    for (int i = 0; i < recordArray.size(); i++) {
                        JSONObject job = recordArray.getJSONObject(i);
                        Set<String> ks = job.keySet();
                        Iterator<String> iterator = ks.iterator();
                        while (iterator.hasNext()) {
                            String k = (String) iterator.next();
                            String v = job.getString(k);
                            if(v=="null"||v==null||v==""){

                            }else {
                                resultMap.put(k + i, v);
                            }
//								System.out.println("  " + k + "=" + v);

                        }

                    }

                }

            } else {
                if(value=="null"||value==null||value.length()==0){

                }else {
                    resultMap.put(key, value);
                }

            }
        }
        return resultMap;
    }
}
