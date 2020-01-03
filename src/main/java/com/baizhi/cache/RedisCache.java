package com.baizhi.cache;

import com.baizhi.conf.GetBeanUtil;
import com.baizhi.util.SerializeUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.locks.ReadWriteLock;


public class RedisCache implements Cache {
    private String id;

    public RedisCache(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    /*
        添加缓存
    *  o   key
    *  o1  value
    * */
    public void putObject(Object o, Object o1) {
        System.out.println("添加缓存");
        StringRedisTemplate StringRedisTemplate = (org.springframework.data.redis.core.StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        /*
         * 大key   --->  id   dao的全限定名
         * 小key   --->  o
         * value   --->  o1
         * */
        if(!ObjectUtils.isEmpty(o1)){
            HashOperations<String, Object, Object> stringObjectObjectHashOperations = StringRedisTemplate.opsForHash();
            String serialize = SerializeUtils.serialize(o1);
            stringObjectObjectHashOperations.put(id.toString(),o.toString(),serialize);
        }
    }
    /*
    * 取缓存
    *
    * */
    public Object getObject(Object o) {
        System.out.println("取缓存");
        StringRedisTemplate stringRedisTemplate = (org.springframework.data.redis.core.StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
        String o1 = (String) stringObjectObjectHashOperations.get(id.toString(), o.toString());
        if(!StringUtils.isEmpty(o1)){
            Object o2 = SerializeUtils.serializeToObject(o1);
            return o2;
        }
        return null;
    }

    /*
    * 自己研究
    * */
    public Object removeObject(Object o) {
        System.out.println("我是  removeObject");
        return null;
    }

    /*
    * 清除缓存
    * */
    public void clear() {
        StringRedisTemplate stringRedisTemplate = (org.springframework.data.redis.core.StringRedisTemplate) GetBeanUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.delete(id);
        System.out.println("我是  clear");
    }

    @Override
    public int getSize() {
        return 0;
    }

    /*
    *  redis  单线程 单进程
    * */
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
