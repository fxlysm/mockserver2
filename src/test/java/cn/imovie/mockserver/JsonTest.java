package cn.imovie.mockserver;

import java.io.StringReader;

import com.google.gson.stream.JsonReader;

public class JsonTest {
    public static void parseJson(String jsonData)
    {
        try
        {
            JsonReader reader = new JsonReader(new StringReader(jsonData));
            reader.beginObject();
            while(reader.hasNext())
            {
                String name = reader.nextName();
                System.out.println(name);
                reader.beginObject();
                String n = reader.nextName();
                System.out.println(n);
                reader.beginArray();
                while(reader.hasNext())
                {
                    reader.beginArray();
                    while(reader.hasNext())
                    {
                        reader.beginArray();
                        while(reader.hasNext())
                        {
                            double x = reader.nextDouble();
                            System.out.println(x);
                        }
                        reader.endArray();
                    }
                    reader.endArray();
                }
                reader.endArray();
            }
            reader.endObject();
            reader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }




}
