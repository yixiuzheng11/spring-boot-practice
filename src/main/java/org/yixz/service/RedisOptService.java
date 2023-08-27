package org.yixz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类，使用之前请确保RedisTemplate成功注入
 *
 * @author yixz
 * @version 2022-02-27
 */
@Component
public class RedisOptService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 判断key是否存在
     *
     * @param key key值
     * @return 是否存在
     */
    public Boolean hasKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {

        Boolean ret = redisTemplate.expire(key, timeout, unit);
        return ret != null && ret;
    }

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public boolean del(final String key) {

        Boolean ret = redisTemplate.delete(key);
        return ret != null && ret;
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public long del(final Collection<String> keys) {

        Long ret = redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }

    /**
     * 存入普通对象
     *
     * @param key   Redis键
     * @param value 值
     */
    public void set(final String key, final Object value) {

        redisTemplate.opsForValue().set(key, value);
    }

    // 存储普通对象操作

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    public void set(final String key, final Object value, final long timeout) {

        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 存入普通对象过期时间为当天24点
     *
     * @param key   键
     * @param value 值
     */
    public void setToDay(final String key, final Object value) {
        LocalDateTime midnight = LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(new Date().toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        redisTemplate.opsForValue().set(key, value,seconds, TimeUnit.SECONDS);
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public Object get(final String key) {

        return redisTemplate.opsForValue().get(key);
    }

    // 存储Hash操作

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public void hPut(final String key, final String hKey, final Object value) {

        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key    Redis键
     * @param values Hash键值对
     */
    public void hPutAll(final String key, final Map<String, Object> values) {

        redisTemplate.opsForHash().putAll(key, values);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public Object hGet(final String key, final String hKey) {

        return redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public List<Object> hMultiGet(final String key, final Collection<Object> hKeys) {

        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    public long sSet(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public long sDel(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 获得set所有成员
     *
     * @param key    Redis键
     * @return 移除的个数
     */
    public Set<String> sMembers(final String key) {
        Set<String> resultSet =redisTemplate.opsForSet().members(key);
        return resultSet;
    }

    /**
     * 查询set是否包含某个值
     *
     * @param key    Redis键
     * @return 移除的个数
     */
    public Boolean sIsMember(final String key,final Object value) {
        Boolean isMember = redisTemplate.opsForSet().isMember(key, value);
        return isMember;
    }

    // 存储List相关操作

    /**
     * 往List中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public long lPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public long lPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public long lPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public List<Object> lGet(final String key, final int start, final int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 存在则设置，不存在则不再设置
     */
    public boolean setIfNotAbsent(final String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     *
     */
    public Long delByPattern(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            return redisTemplate.delete(keys);
        } else {
            return 0L;
        }

    }

    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);
    }


    /**
     * 批量删除key
     *
     * @param keys
     * @return
     */
    public Long delKeys(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 增加(自增长), 负数则为自减
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }


    public Integer getIncrValue(String key) {
        return (Integer) redisTemplate.execute((RedisCallback<Integer>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] rowkey = serializer.serialize(key);
            byte[] rowval = connection.get(rowkey);
            try {
                String val = serializer.deserialize(rowval);
                return Integer.parseInt(val);
            } catch (Exception e) {
                return 0;
            }
        });
    }

    public void zAdd(String key,String value,Double score){
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<String> zRevRangeAll(String key){
        return this.zRevRange(key,0,-1);
    }

    public Set<String> zRevRange(String key,long start,long stop){
        return redisTemplate.opsForZSet().reverseRange(key, start, stop);
    }

    public Set<String> zRange(String key,long start,long end){
        return redisTemplate.opsForZSet().range(key,start,end);
    }

    public Set<String> zRangeAll(String key){
        return this.zRange(key,0,-1);
    }

    public Boolean setIfAbsent(final String key, final Object value, final long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }
}
