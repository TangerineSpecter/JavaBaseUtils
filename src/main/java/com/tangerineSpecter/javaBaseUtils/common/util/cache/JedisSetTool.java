package com.tangerineSpecter.javaBaseUtils.common.util.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Jedis工具类（Set）
 *
 * @author TangerineSpecter
 */
@Slf4j
public class JedisSetTool extends JedisTool {

    /**
     * 设置缓存数据到Set集合中
     *
     * @param key
     * @param value
     */
    public static void setSetCache(String key, String value) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.sadd(key, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                setSetCache(key, value);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存数据到Set集合中
     *
     * @param key
     */
    public static Set<String> getSetCache(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.smembers(key);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            return new HashSet<String>();
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
            return new HashSet<String>();
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                return getSetCache(key);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存数据到Set集合中
     *
     * @param key
     */
    public static Set<? extends Serializable> getSetObj(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            Set<byte[]> byteValues = jedis.smembers(key.getBytes());
            if (byteValues == null) {
                return new HashSet<Serializable>();
            }

            Set<Serializable> values = new HashSet<Serializable>(byteValues.size());
            for (byte[] byteValue : byteValues) {
                values.add((Serializable) SerializationUtils.deserialize(byteValue));
            }

            return values;
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            return new HashSet<String>();
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
            return new HashSet<String>();
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                return getSetObj(key);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 删除set集合中的指定数据
     *
     * @param key
     * @param value
     */
    public static void delSetValue(String key, String value) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.srem(key, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis删除缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis删除缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                delSetValue(key, value);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 删除set集合中的指定数据
     *
     * @param key
     * @param value
     */
    public static void delSetObj(String key, Serializable value) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.srem(key.getBytes(), SerializationUtils.serialize(value));
        } catch (JedisConnectionException e1) {
            log.warn("【Redis删除缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis删除缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                delSetObj(key, value);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 删除set集合中的随机元素
     *
     * @param key
     */
    public static void delSetRandom(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.spop(key);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis删除缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis删除缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                delSetRandom(key);
            } else {
                returnResource(jedis);
            }
        }
    }

    /**
     * 获取缓存中set集合的基数
     *
     * @param key
     */
    public static Long getSetSize(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            return jedis.scard(key);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis获取缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis获取缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                return getSetSize(key);
            } else {
                returnResource(jedis);
            }
        }
        return 0L;
    }

    /**
     * 判断set中是否有指定值
     *
     * @param key
     * @param value
     */
    public static boolean checkSetValue(String key, String value) {
        Jedis jedis = null;
        boolean result = false;
        boolean broken = false;
        try {
            jedis = getResource();
            result = jedis.sismember(key, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis获取缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis获取缓存连接失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                return checkSetValue(key, value);
            } else {
                returnResource(jedis);
            }
        }
        return result;
    }
}
