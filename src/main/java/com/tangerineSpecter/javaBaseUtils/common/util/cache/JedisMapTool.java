package com.tangerineSpecter.javaBaseUtils.common.util.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.Serializable;
import java.util.*;

/**
 * Jedis工具类（Map）
 *
 * @author TangerineSpecter
 */
@Slf4j
public class JedisMapTool extends JedisTool {

    /**
     * 向名称为key的hash中添加键值对 field->value
     *
     * @param key
     * @param field
     * @param value
     */
    public static void setMapCache(String key, String field, String value) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hset(key, field, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setMapCache(key, field, value);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 获取名称为key的集合中field元素的值
     *
     * @param key
     * @param field
     */
    public static String getMapValue(String key, String field) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hget(key, field);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getMapValue(key, field);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return null;
    }

    /**
     * 设置Map缓存
     *
     * @param key
     * @param field
     * @param o
     */
    public static void setMapObj(String key, String field, Serializable o) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hset(key.getBytes(), field.getBytes(), SerializationUtils.serialize(o));
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setMapObj(key, field, o);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 获取Map缓存
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getMapObj(String key, String field) {
        Object o = null;
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            byte[] temp = jedis.hget(key.getBytes(), field.getBytes());
            if (temp != null) {
                o = SerializationUtils.deserialize(temp);
            }
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getMapObj(key, field);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return o;
    }

    /**
     * 设置Map缓存（Map形式）
     *
     * @param key
     * @param values
     */
    public static <T extends Serializable> void setMapObjs(String key, Map<String, T> values) {
        Map<byte[], byte[]> byteValues = new HashMap<byte[], byte[]>();
        for (String field : values.keySet()) {
            byteValues.put(field.getBytes(), SerializationUtils.serialize(values.get(field)));
        }

        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hmset(key.getBytes(), byteValues);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setMapObjs(key, values);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 获取Map缓存（Map形式）
     *
     * @param key
     * @return
     */
    public static Map<String, ? extends Serializable> getMapObjs(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<byte[]> keys = jedis.hkeys(key.getBytes());
            if (keys == null || keys.isEmpty()) {
                return null;
            }
            byte[][] keyArr = new byte[keys.size()][];
            keyArr = keys.toArray(keyArr);
            List<byte[]> byteValues = jedis.hmget(key.getBytes(), keyArr);
            Map<String, Serializable> values = new HashMap<String, Serializable>();
            for (int i = 0; i < keyArr.length; i++) {
                byte[] byteKey = keyArr[i];
                byte[] byteValue = byteValues.get(i);
                values.put(new String(byteKey), (Serializable) SerializationUtils.deserialize(byteValue));
            }
            return values;
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getMapObjs(key);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return null;
    }

    /**
     * 返回名称为key的hash中所有键
     *
     * @param key
     */
    public static Set<String> getMapKeys(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hkeys(key);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getMapKeys(key);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return new HashSet<String>();
    }

    /**
     * 返回名称为key的hash中所有的键（field）及其对应的value
     *
     * @param key
     */
    public static Map<String, String> getMapKeyValue(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.hgetAll(key);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getMapKeyValue(key);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return new HashMap<String, String>();
    }

    /**
     * 删除map中的指定键值对
     *
     * @param key
     * @param field
     */
    public static void delMapValue(String key, String field) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.hdel(key, field);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            delMapValue(key, field);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }
}
