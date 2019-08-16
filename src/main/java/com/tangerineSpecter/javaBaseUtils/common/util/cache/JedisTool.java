package com.tangerinespecter.javabaseutils.common.util.cache;

import com.tangerinespecter.javabaseutils.common.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.Serializable;

/**
 * redis工具类
 *
 * @author TangerineSpecter
 */
@Slf4j
public class JedisTool {

    // redis连接池
    private static JedisPool pool = null;

    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Constant.Redis.REIDS_MAX_ACTIVE);
            config.setMaxIdle(Constant.Redis.REIDS_MAX_IDLE);
            config.setMinIdle(Constant.Redis.REIDS_MIN_IDLE);
            config.setMaxWaitMillis(Constant.Redis.REIDS_MAX_WAITTIME);
            config.setTestOnBorrow(false);
            config.setTestOnReturn(false);
            config.setTestWhileIdle(true);
            config.setTimeBetweenEvictionRunsMillis(1000);
            config.setMinEvictableIdleTimeMillis(1000);
            log.info("Redis host：" + Constant.Redis.REDIS_IP);
            pool = new JedisPool(config, Constant.Redis.REDIS_IP, 6379, 3000, Constant.Redis.REDIS_PASSWD);
        }
        return pool;
    }

    /**
     * 获取redis资源
     *
     * @return
     */
    public static Jedis getResource() {
        Jedis jedis = getPool().getResource();
        return jedis;
    }

    /**
     * 释放资源
     *
     * @param redis
     */
    public static void returnBrokenResource(Jedis redis) {
        if (redis != null) {
            Jedis resource = getPool().getResource();
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * 释放资源
     *
     * @param redis
     */
    public static void returnResource(Jedis redis) {
        if (redis != null) {
            Jedis resource = getPool().getResource();
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public static void setCache(String key, String value) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.set(key, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setCache(key, value);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param o
     */
    public static void setCache(String key, Serializable o) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.set(key.getBytes(), SerializationUtils.serialize(o));
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setCache(key, o);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存 带缓存时间的
     *
     * @param key
     * @param value
     * @param seconds 精确到秒
     */
    public static void setCache(String key, String value, int seconds) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.setex(key, seconds, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setCache(key, value, seconds);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存 带缓存时间的
     *
     * @param key
     * @param o
     * @param seconds 精确到秒
     */
    public static void setCache(String key, Serializable o, int seconds) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.setex(key.getBytes(), seconds, SerializationUtils.serialize(o));
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setCache(key, o, seconds);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 设置缓存 多个二进制键值对
     *
     * @param keyvalue
     */
    public static void setListCache(byte[]... keyvalue) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.mset(keyvalue);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setListCache(keyvalue);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 缓存二进制数据到关键字
     *
     * @param key
     * @param data
     */
    public static void setCache(String key, byte[] data) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.set(key.getBytes(), data);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            setCache(key, data);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

    /**
     * 当且仅当 key不存在, 将 key的值设为 value, 并返回1;若给定的 key已经存在,则不做任何动作，并返回0,异常时返回2
     *
     * @param key
     * @param value
     * @return
     */
    public static int setnx(String key, String value) {
        Jedis jedis = null;
        int flag = 2;
        boolean broken = false;
        try {
            jedis = getResource();
            flag = jedis.setnx(key, value).intValue();
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return setnx(key, value);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return flag;
    }

    /**
     * 当且仅当 key不存在,将 key的值设为 value, 并返回1;若给定的 key已经存在,则 不做任何动作，并返回0,异常时返回2
     *
     * @param key
     * @param o
     * @return
     */
    public static int setnx(String key, Serializable o) {
        Jedis jedis = null;
        int flag = 2;
        boolean broken = false;
        try {
            jedis = getResource();
            flag = jedis.setnx(key.getBytes(), SerializationUtils.serialize(o)).intValue();
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (broken) {
                returnBrokenResource(jedis);
                flag = setnx(key, SerializationUtils.serialize(o));
            } else {
                returnResource(jedis);
            }
        }
        return flag;
    }

    /**
     * 设置过期时间
     *
     * @param key     key 值
     * @param seconds 过期时间
     * @return
     */
    public static int setExpire(String key, int seconds) {
        Jedis jedis = null;
        int flag = 2;
        boolean broken = false;
        try {
            jedis = getResource();
            flag = jedis.expire(key, seconds).intValue();
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return jedis.expire(key, seconds).intValue();
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return flag;
    }

    /**
     * 当且仅当 key不存在, 将 key的值设为 value, 并返回1;若给定的 key已经存在,则 不做任何动作，并返回0,异常时返回2
     *
     * @param key key值
     * @param obj 二进制数据
     */
    public static int setnx(String key, byte[] obj) {
        Jedis jedis = null;
        int flag = 2;
        boolean broken = false;
        try {
            jedis = getResource();
            flag = jedis.setnx(key.getBytes(), obj).intValue();
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return setnx(key, obj);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return flag;
    }

    /**
     * 缓存字符串到关键字key 返回key上一次存储的字符串
     *
     * @param key
     * @param value
     */
    protected static String getset(String key, String value) {
        Jedis jedis = null;
        String flag = "";
        boolean broken = false;
        try {
            jedis = getResource();
            flag = jedis.getSet(key, value);
        } catch (JedisConnectionException e1) {
            log.warn("【Redis设置缓存连接失败】：" + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getset(key, value);
        } catch (Exception e) {
            log.error("【Redis设置缓存失败】：" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return flag;
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static String getValCache(String key) {
        String o = null;
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            o = jedis.get(key);
        } catch (JedisConnectionException e1) {
            log.warn(String.format("Redis getValCache %s conn fail: %s ", key, e1.toString()));
            broken = true;
            returnBrokenResource(jedis);
            return getValCache(key);
        } catch (Exception e) {
            log.error(String.format("Redis getValCache %s fail: %s ", key, e.toString()), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return o;
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object getObjCache(String key) {
        Object o = null;
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            byte[] temp = jedis.get(key.getBytes());
            if (temp != null) {
                o = SerializationUtils.deserialize(temp);
            }
        } catch (JedisConnectionException e1) {
            log.warn("Redis getObjCache conn fail: " + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getObjCache(key);
        } catch (Exception e) {
            log.error("Redis getObjCache fail: " + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return o;
    }

    /**
     * 根据关键字获取二进制数据缓存
     *
     * @param key
     */
    public static byte[] getDataCache(String key) {
        Jedis jedis = null;
        byte[] data = null;
        boolean broken = false;
        try {
            jedis = getResource();
            data = jedis.get(key.getBytes());
        } catch (JedisConnectionException e1) {
            log.warn("Redis getDataCache conn fail: " + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            return getDataCache(key);
        } catch (Exception e) {
            log.error("Redis getDataCache fail" + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
        return data;
    }

    /**
     * 删除缓存数据
     *
     * @param key
     */
    public static void delCache(String key) {
        Jedis jedis = null;
        boolean broken = false;
        try {
            jedis = getResource();
            jedis.del(key.getBytes());
        } catch (JedisConnectionException e1) {
            log.warn("Redis delCache conn fail: " + e1.toString());
            broken = true;
            returnBrokenResource(jedis);
            delCache(key);
        } catch (Exception e) {
            log.error("Redis delCache fail: " + e.toString(), e);
        } finally {
            if (!broken) {
                returnResource(jedis);
            }
        }
    }

}
