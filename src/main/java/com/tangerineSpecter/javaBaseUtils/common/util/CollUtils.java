package com.tangerinespecter.javabaseutils.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collection 工具类
 *
 * @author TangerineSpecter
 * @date 2023年11月30日11:02:40
 */
@SuppressWarnings("all")
public class CollUtils {

    /**
     * 包含任意一个元素返回true
     *
     * @param source  原始数据
     * @param targets 校验数据数组
     * @return true：包含
     */
    public static boolean containsAny(Object source, Object... targets) {
        return Arrays.asList(targets).contains(source);
    }

    /**
     * 任意一个数据为空
     *
     * @param collections 数据集合
     * @return true：存在空数据
     */
    public static boolean isAnyEmpty(Collection<?>... collections) {
        return Arrays.stream(collections).anyMatch(CollectionUtil::isEmpty);
    }

    /**
     * 过滤出符合条件的list数据
     *
     * @param from      原始集合
     * @param predicate 过滤条件
     * @param <T>       返回对象
     * @return 符合条件的list
     */
    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出符合条件的Set数据
     *
     * @param from      原始集合
     * @param predicate 过滤条件
     * @param <T>       返回对象
     * @return 符合条件的set
     */
    public static <T> Set<T> filterSet(Collection<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * 过滤出符合条件的Set数据
     *
     * @param from      原始集合
     * @param predicate 过滤条件
     * @param func      映射方法
     * @param <T>       返回对象
     * @param <U>       映射对象
     * @return 符合条件的set
     */
    public static <T, U> Set<U> filterSet(Collection<T> from, Predicate<T> predicate, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptySet();
        }
        return from.stream().filter(predicate).map(func).collect(Collectors.toSet());
    }

    /**
     * list去重返回结果
     *
     * @param from      原始list
     * @param keyMapper 映射方法
     * @param <T>       原始对象
     * @param <R>       映射对象
     * @return 去重list
     */
    public static <T, R> List<T> distinct(Collection<T> from, Function<T, R> keyMapper) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return distinct(from, keyMapper, (t1, t2) -> t1);
    }

    /**
     * list去重返回结果
     *
     * @param from      原始list
     * @param keyMapper 映射方法
     * @param cover     运算函数，接收参数和返回参数类型一致。(x1,x2) -> x1
     * @param <T>       原始对象
     * @param <R>       映射对象
     * @return 去重list
     */
    public static <T, R> List<T> distinct(Collection<T> from, Function<T, R> keyMapper, BinaryOperator<T> cover) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(convertMap(from, keyMapper, Function.identity(), cover).values());
    }

    /**
     * list聚合映射返回集合
     *
     * @param from 原始list
     * @param func 映射方法
     * @param <T>  原始对象
     * @param <U>  映射对象
     * @return 聚合结果list
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).collect(Collectors.toList());
    }

    /**
     * 聚合list，无数据返回带默认值的list
     *
     * @param from         原始list
     * @param func         映射方法
     * @param defaultValue 默认值
     * @param <T>          原始对象
     * @param <U>          映射对象
     * @return 映射返回list
     */
    public static <T, U> List<U> convertListOrDefault(Collection<T> from, Function<T, U> func, U defaultValue) {
        if (CollUtil.isEmpty(from)) {
            return CollUtil.newArrayList(defaultValue);
        }
        return from.stream().map(func).collect(Collectors.toList());
    }

    /**
     * set聚合映射返回集合
     *
     * @param from 原始set
     * @param func 映射方法
     * @param <T>  原始对象
     * @param <U>  映射对象
     * @return 聚合结果set
     */
    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).collect(Collectors.toSet());
    }

    /**
     * 聚合set，无数据返回带默认值的set
     *
     * @param from         原始set
     * @param func         映射方法
     * @param defaultValue 默认值
     * @param <T>          原始对象
     * @param <U>          映射对象
     * @return 映射返回set
     */
    public static <T, U> Set<U> convertSetOrDefault(Collection<T> from, Function<T, U> func, U defaultValue) {
        if (CollUtil.isEmpty(from)) {
            return CollUtil.newHashSet(defaultValue);
        }
        return from.stream().map(func).collect(Collectors.toSet());
    }

    /**
     * 聚合返回map
     *
     * @param from    原始list
     * @param keyFunc key映射方法
     * @param <T>     原始对象
     * @param <K>     映射对象
     * @return 聚合返回map结果
     */
    public static <T, K> Map<K, T> convertMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, Function.identity());
    }

    /**
     * 聚合返回map
     *
     * @param from     原始list
     * @param keyFunc  key映射方法
     * @param supplier
     * @param <T>      原始对象
     * @param <K>      映射对象
     * @return 聚合返回map结果
     */
    public static <T, K> Map<K, T> convertMap(Collection<T> from, Function<T, K> keyFunc, Supplier<? extends Map<K, T>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return supplier.get();
        }
        return convertMap(from, keyFunc, Function.identity(), supplier);
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1);
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc, BinaryOperator<V> mergeFunction) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, mergeFunction, HashMap::new);
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc, Supplier<? extends Map<K, V>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return supplier.get();
        }
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1, supplier);
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc, BinaryOperator<V> mergeFunction, Supplier<? extends Map<K, V>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.toMap(keyFunc, valueFunc, mergeFunction, supplier));
    }

    public static <T, K> Map<K, List<T>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(t -> t, Collectors.toList())));
    }

    public static <T, K, V> Map<K, List<V>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(valueFunc, Collectors.toList())));
    }

    // 暂时没想好名字，先以 2 结尾噶
    public static <T, K, V> Map<K, Set<V>> convertMultiMap2(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(valueFunc, Collectors.toSet())));
    }

    /**
     * 目标集合中任意一个元素在原始集合中，则返回ture
     *
     * @param source     原始集合
     * @param candidates 目标集合
     * @return true：存在任意元素
     */
    public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
        return CollectionUtils.containsAny(source, candidates);
    }

    /**
     * 获取集合第一个元素，不存在则返回null
     *
     * @param from list集合
     * @param <T>  集合对象
     * @return 第一个元素
     */
    public static <T> T getFirst(List<T> from) {
        return !CollectionUtil.isEmpty(from) ? from.get(0) : null;
    }

    /**
     * 过滤出符合条件的结果集，并获取集合中第一个元素
     * 空集合则返回null
     *
     * @param from      list集合
     * @param predicate 过滤条件
     * @param <T>       集合对象
     * @return 第一个结果
     */
    public static <T> T findFirst(List<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return null;
        }
        return from.stream().filter(predicate).findFirst().orElse(null);
    }

    /**
     * 集合追加元素
     * 元素为空，则跳过
     *
     * @param coll 集合
     * @param item 元素
     * @param <T>  集合对象
     */
    public static <T> void addIfNotNull(Collection<T> coll, T item) {
        if (item == null) {
            return;
        }
        coll.add(item);
    }

    /**
     * 遍历集合元素
     * 空集合则跳过
     *
     * @param coll     集合
     * @param consumer 遍历执行方法
     * @param <T>      原始对象
     */
    public static <T> void forEach(Collection<T> coll, Consumer<T> consumer) {
        if (CollUtil.isEmpty(coll)) {
            return;
        }
        coll.forEach(consumer);
    }

    /**
     * 根据条件过滤聚合list
     *
     * @param from      原始list
     * @param predicate 过滤条件
     * @param func      映射方法
     * @param <T>       原始对象
     * @param <U>       映射对象
     * @return 映射结果list
     */
    public static <T, U> List<U> filterConvertList(Collection<T> from, Predicate<T> predicate, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptyList();
        }
        return from.stream().filter(predicate).map(func).collect(Collectors.toList());
    }

    public static <T, K, V> Map<K, V> convertConcurrentHashMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new ConcurrentHashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1, ConcurrentHashMap::new);
    }

    /**
     * 根据条件过滤符合条件的集合，并获取第一个元素。不存在则返回默认值
     *
     * @param from       原始集合
     * @param predicate  过滤条件
     * @param defaultObj 默认值
     * @param <T>        集合对象
     * @return 符合条件的第一个元素
     */
    public static <T> T filterFirst(Collection<T> from, Predicate<T> predicate, T defaultObj) {
        if (CollUtil.isEmpty(from)) {
            return defaultObj;
        }
        Optional<T> optional = from.stream().filter(predicate).findFirst();
        return optional.orElse(defaultObj);
    }

    /**
     * 聚合list，并对结果集去重
     *
     * @param from 原始集合
     * @param func 映射方法
     * @param <T>  原始对象
     * @param <U>  映射对象
     * @return list聚合结果
     */
    public static <T, U> List<U> convertDistinctList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptyList();
        }
        return from.stream().map(func).distinct().collect(Collectors.toList());
    }

    public static <T, U, R, S> Set<S> convertFlat2Set(Collection<T> from, Function<T, U> func, Function<U, Stream<R>> mapper, Function<R, S> flatFunc) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptySet();
        }
        return from.stream().map(func).flatMap(mapper).map(flatFunc).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, Long> convertCountMultiMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptyMap();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(t -> t, Collectors.counting())));
    }

    public static <T> Map<Boolean, List<T>> partitioningMap(Collection<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptyMap();
        }
        return from.stream().collect(Collectors.partitioningBy(predicate));
    }

    /**
     * 获取集合第一个元素，如果集合为空，则返回默认值
     *
     * @param from         list集合
     * @param defaultValue 默认值
     * @param <T>          集合对象
     * @return 第一个元素
     */
    public static <T> T getFirstOrDefault(List<T> from, T defaultValue) {
        if (CollUtil.isEmpty(from)) {
            return defaultValue;
        }
        return from.stream().findFirst().get();
    }

    /**
     * 分页聚合，并根据条件排序
     *
     * @param from       原始集合
     * @param comparator 排序条件
     * @param page       起始页，0
     * @param pageSize   分页大小
     * @param <U>        集合对象
     * @return 分页结果
     */
    public static <U> List<U> convertSortPage(Collection<U> from, Comparator<U> comparator, Integer page, Integer pageSize) {
        if (CollUtil.isEmpty(from) || page <= 0 || pageSize <= 0) {
            return new ArrayList<>();
        }
        return from.stream().sorted(comparator).skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    /**
     * 聚合求和
     *
     * @param coll   对象集合
     * @param mapper 求和属性
     * @param <T>    求和对象
     * @return 求和结果，空则返回0
     */
    public static <T> Long convert2Sum(Collection<T> coll, ToLongFunction<? super T> mapper) {
        if (CollUtil.isEmpty(coll)) {
            return 0L;
        }
        return coll.stream().mapToLong(mapper).sum();
    }

    /**
     * 任意满足则返回true
     *
     * @param coll      对象集合
     * @param predicate 过滤条件
     * @return true：满足
     */
    public static <T> boolean anyMatch(Collection<T> coll, Predicate<? super T> predicate) {
        if (CollUtil.isEmpty(coll)) {
            return false;
        }
        return coll.stream().anyMatch(predicate);
    }

}
