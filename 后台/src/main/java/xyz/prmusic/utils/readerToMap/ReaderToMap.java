package xyz.prmusic.utils.readerToMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.prmusic.utils.crypto.AESUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReaderToMap {
    public static Map<String,String> getObjToMap(BufferedReader bufferedReader,boolean isdecrypt) throws Exception {
        if (bufferedReader == null){
            throw new IOException("流内容为空");
        }
        BufferedReader in = new BufferedReader(bufferedReader);
        StringBuffer buffer = new StringBuffer(in.readLine());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(buffer.toString(), HashMap.class);
        if (isdecrypt){
            map = objectMapper.readValue(AESUtil.aesDecrypt(map.get("data"),"Dn0lfj3sGGh73XOZ"),HashMap.class);
        }
        return map;
    }
}
