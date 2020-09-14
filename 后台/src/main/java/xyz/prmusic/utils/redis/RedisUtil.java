package xyz.prmusic.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtil implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(Object key, String value) {
        if (key == null) {
            throw new NullPointerException("key值不能为空");
        }
        if (value == null) {
            throw new NullPointerException("value值不能为空");
        }
        redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public void addTimeLimit(Object key, Object value, Long time) {
        if (key == null) {
            throw new NullPointerException("key值不能为空");
        }
        if (value == null) {
            throw new NullPointerException("value值不能为空");
        }
        if (time == null) {
            throw new NullPointerException("time值不能为空");
        }
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    @Override
    public void addObj(Object objectKey, Object key, Object object) {
        if (objectKey == null) {
            throw new NullPointerException("objectKey值不能为空");
        }
        if (key == null) {
            throw new NullPointerException("key值不能为空");
        }
        if (object == null) {
            throw new NullPointerException("object值不能为空");
        }
        redisTemplate.opsForHash().put(objectKey, key, object);
    }

    @Override
    public void delete(Object key) {
        if (key == null) {
            throw new NullPointerException("key值不能为空");
        }
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("collection不能为空");
        }
        redisTemplate.delete(collection);
    }

    public String getKey(String key) {
        return key == null ? null : String.valueOf(redisTemplate.opsForValue().get(key));
    }

    @Override
    public Object getObj(Object objectKey, Object key) {
        if (objectKey == null) {
            throw new NullPointerException("objectKey值不能为空");
        }
        if (key == null) {
            throw new NullPointerException("key值不能为空");
        }
        return redisTemplate.opsForHash().delete(key, objectKey);
    }

    @Override
    public List getList(String key) {
        return key == null ? null :(List<String>) redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public void deleteList(String key,int count,Object value) {
        if (key == null){
            throw new NullPointerException("key值不能为空");
        }
        if (value == null){
            throw new NullPointerException("value值不能为空");
        }
        redisTemplate.opsForList().remove(key,count,value);
    }
}
