package xyz.prmusic.utils.result;

import java.io.Serializable;

public class ResultMessageEntity implements Serializable {
    private int statusCode;
    private Object message;

    public ResultMessageEntity() {
    }

    public ResultMessageEntity(int statusCode, Object message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
