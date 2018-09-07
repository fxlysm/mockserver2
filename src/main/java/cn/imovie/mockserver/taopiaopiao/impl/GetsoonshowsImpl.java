package cn.imovie.mockserver.taopiaopiao.impl;

import cn.imovie.mockserver.taopiaopiao.controller.Getsoonshows;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Map;

@Service
public class GetsoonshowsImpl implements Getsoonshows {


    public void Getsoonshows(Map map, PrintWriter out){
        JSONObject result = new JSONObject();
        result.put("filmname","我不是药神");
        out.println(result);
    }
}
