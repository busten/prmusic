package xyz.prmusic.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public interface RedisService<H, K, V> {
    void add(K key, String value);

    void addTimeLimit(K key, Object value, Long time);

    void addObj(H objectKey, K key, V object);

    void delete(K key);

    void delete(Collection<K> collection);

    String getKey(String key);

    V getObj(H objectKey, K key);

    List getList(String key);

    void deleteList(String key, int count, Object value);
}
