package cn.imovie.mockserver.exception;

import java.io.IOException;

public class MyIOException  extends IOException {
    public MyIOException(String message) {
        super(message);
    }
}
