package com.tangerinespecter.javabaseutils.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 链式计算
 *
 * @author TangerineSpecter
 * @date 2023年11月30日11:02:26
 */
public class NumChainCal {

    private BigDecimal value;

    public NumChainCal(Object value) {
        this.value = Convert.toBigDecimal(value);
    }

    /**
     * 计算的开始
     *
     * @param value 初始值
     * @return 计算器
     */
    public static NumChainCal startOf(Object value) {
        if (value == null) {
            return new NumChainCal(0);
        }
        return new NumChainCal(value);
    }

    /**
     * 加法
     *
     * @param otherValue 加数
     * @return 结果
     */
    public NumChainCal add(Object otherValue) {
        this.value = NumberUtil.add(this.value, Convert.toBigDecimal(otherValue));
        return this;
    }

    /**
     * 加法
     *
     * @param otherValue 加数
     * @return 结果
     */
    public NumChainCal add(BigDecimal... otherValue) {
        BigDecimal totalValue = NumberUtil.add(otherValue);
        this.value = NumberUtil.add(this.value, totalValue);
        return this;
    }

    /**
     * 减法
     *
     * @param otherValue 减数
     * @return 结果
     */
    public NumChainCal sub(Object otherValue) {
        this.value = NumberUtil.sub(this.value, Convert.toBigDecimal(otherValue));
        return this;
    }

    /**
     * 乘法
     *
     * @param otherValue 乘数
     * @return 结果
     */
    public NumChainCal mul(Object otherValue) {
        this.value = NumberUtil.mul(this.value, Convert.toBigDecimal(otherValue));
        return this;
    }

    /**
     * 除法
     *
     * @param otherValue 除数
     * @return 结果
     */
    public NumChainCal div(Object otherValue) {
        BigDecimal convertValue = Convert.toBigDecimal(otherValue);
        if (convertValue == null || convertValue.equals(BigDecimal.ZERO)) {
            return new NumChainCal(0);
        }
        this.value = NumberUtil.div(this.value, Convert.toBigDecimal(otherValue));
        return this;
    }

    /**
     * 除法
     *
     * @param otherValue 除数
     * @param scale      – 精确度，如果为负值，取绝对值
     * @return 结果
     */
    public NumChainCal div(Object otherValue, int scale) {
        BigDecimal convertValue = Convert.toBigDecimal(otherValue);
        if (convertValue.equals(BigDecimal.ZERO)) {
            return new NumChainCal(0);
        }
        this.value = NumberUtil.div(this.value, convertValue, scale);
        return this;
    }

    /**
     * 除法
     *
     * @param otherValue   除数
     * @param scale        – 精确度，如果为负值，取绝对值
     * @param roundingMode 保留小数的模式
     * @return 结果
     */
    public NumChainCal div(Object otherValue, int scale, RoundingMode roundingMode) {
        BigDecimal convertValue = Convert.toBigDecimal(otherValue);
        if (convertValue.equals(BigDecimal.ZERO)) {
            return new NumChainCal(0);
        }
        this.value = NumberUtil.div(this.value, convertValue, scale, roundingMode);
        return this;
    }

    /**
     * 获取int数值
     *
     * @return int结果
     */
    public Integer getInteger() {
        return Convert.toInt(this.value);
    }

    /**
     * 获取int数值
     *
     * @param roundingMode 取整方式
     * @return int结果
     */
    public Integer getInteger(RoundingMode roundingMode) {
        return Convert.toInt(this.value.setScale(0, roundingMode));
    }

    /**
     * 获取Long数值
     *
     * @return int结果
     */
    public Long getLong() {
        return Convert.toLong(this.value);
    }

    /**
     * 获取BigDecimal数值
     *
     * @return BigDecimal结果
     */
    public BigDecimal getBigDecimal() {
        return Convert.toBigDecimal(this.value).setScale(4, RoundingMode.CEILING);
    }

    /**
     * 获取BigDecimal数值
     *
     * @param scale 保留小数位
     * @return BigDecimal结果
     */
    public BigDecimal getBigDecimal(int scale) {
        return Convert.toBigDecimal(this.value).setScale(scale, RoundingMode.CEILING);
    }

    /**
     * 获取BigDecimal数值
     *
     * @param scale        保留小数位
     * @param roundingMode 舍入模式
     * @return BigDecimal结果
     */
    public BigDecimal getBigDecimal(int scale, RoundingMode roundingMode) {
        return Convert.toBigDecimal(this.value).setScale(scale, roundingMode);
    }
}
