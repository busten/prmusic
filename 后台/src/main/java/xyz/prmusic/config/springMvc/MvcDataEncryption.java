package xyz.prmusic.config.springMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.prmusic.utils.crypto.AESUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MvcDataEncryption extends HttpServletRequestWrapper {

    public MvcDataEncryption(HttpServletRequest request) {
        super(request);
    }

    public Map<String, String> getDataFromMap(boolean isdecrypt) throws IOException {
        BufferedReader in = new BufferedReader(super.getReader());
        StringBuffer buffer = new StringBuffer();
        String line = in.readLine();
        if (line != null) {
            buffer.append(line);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(buffer.toString(), HashMap.class);
            if (isdecrypt) {
                try {
                    map = objectMapper.readValue(AESUtil.aesDecrypt(map.get("data"),"Dn0lfj3sGGh73XOZ"), HashMap.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return map;
        }
        return null;
    }
}
