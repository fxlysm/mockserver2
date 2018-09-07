package cn.imovie.mockserver.Wechat.server;

import java.io.IOException;
import java.util.Map;

public interface payNoticeServer {
    public void  payNotify(Map<String, String> map) throws IOException, InterruptedException;
}
