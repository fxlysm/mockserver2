package cn.imovie.mockserver.taopiaopiao.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public interface Getregions {
    void Getregions(HttpServletResponse resp, Map map)throws IOException;
}
