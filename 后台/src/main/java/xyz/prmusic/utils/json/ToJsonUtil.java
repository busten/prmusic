package xyz.prmusic.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.prmusic.utils.result.ResultMessage;
import xyz.prmusic.utils.result.ResultMessageEntity;

public class ToJsonUtil {
    private int statusCode;
    private Object message;

    public ToJsonUtil() {
    }

    public ToJsonUtil(ResultMessage resultMessage) {
        this.statusCode = resultMessage.getStatusCode();
        this.message = resultMessage.getMessage();
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getJsonMessage() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResultMessageEntity resultMessageEntity = new ResultMessageEntity(this.statusCode,this.message);
        String json = objectMapper.writeValueAsString(resultMessageEntity);
        return json;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
