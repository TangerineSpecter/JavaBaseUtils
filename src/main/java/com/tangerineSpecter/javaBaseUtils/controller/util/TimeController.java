package com.tangerinespecter.javabaseutils.controller.util;

import com.tangerinespecter.javabaseutils.common.util.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 时间工具类
 *
 * @author TangerineSpecter
 */
@Api(tags = "时间工具类")
@RestController
@RequestMapping("/timeUtils")
public class TimeController {

    @GetMapping("/getCurrentYear")
    @ApiOperation("获取当前年份")
    public String getCurrentYear() {
        return TimeUtils.getCurrentYear();
    }

    @GetMapping("/timeDifForYear")
    @ApiOperation("时间差计算(年-月-日)")
    public String timeDifForYear(@ApiParam("开始时间戳") @RequestParam("startTime") Long startTime,
                                 @ApiParam("结束时间戳") @RequestParam("endTime") Long endTime) {
        return TimeUtils.timeDifForYear(startTime, endTime);
    }

    @GetMapping("/timeFormatToDay")
    @ApiOperation("将时间格式精确到天")
    public String timeFormatToDay(@ApiParam("时间") @RequestParam("date") Date date) {
        return TimeUtils.timeFormatToDay(date);
    }

    @GetMapping("/getSimpleFormat")
    @ApiOperation("获取指定格式当前时间")
    public String getSimpleFormat(@ApiParam("时间格式,如:yyyy-MM-dd") @RequestParam("format") String format) {
        return TimeUtils.getSimpleFormat(format);
    }

    @GetMapping("/getCurrentTimes")
    @ApiOperation("获取当前时间戳")
    public Long getCurrentTimes() {
        return TimeUtils.getCurrentTimes();
    }

    @GetMapping("/getDate")
    @ApiOperation("将指定的日期字符串转化为日期对象")
    public Date getDate(@ApiParam("日期字符串") @RequestParam("dateStr") String dateStr,
                        @ApiParam("时间格式,如:yyyy-MM-dd") @RequestParam("format") String format) {
        return TimeUtils.getDate(dateStr, format);
    }

    @GetMapping("/getDateMillion")
    @ApiOperation("将指定格式转换成毫秒")
    public Long getDateMillion(@ApiParam("日期字符串") @RequestParam("dateStr") String dateStr,
                               @ApiParam("时间格式,如:yyyy-MM-dd") @RequestParam("format") String format) {
        return TimeUtils.getDateMillion(dateStr, format);
    }

    @GetMapping("/getDayBeginTimestamp")
    @ApiOperation("获取当天开始时间戳")
    public Long getDayBeginTimestamp() {
        return TimeUtils.getDayBeginTimestamp();
    }

    @GetMapping("/getDayEndTimestamp")
    @ApiOperation("获取当天结束时间戳")
    public Long getDayEndTimestamp() {
        return TimeUtils.getDayEndTimestamp();
    }

    @GetMapping("/getDisparityDay")
    @ApiOperation("获取距离某个日期的天数")
    public Integer getDisparityDay(@ApiParam("时间字符串") @RequestParam("time") String time) {
        return TimeUtils.getDisparityDay(time);
    }

    @GetMapping("/getFinalDay")
    @ApiOperation("获取某年某月最后一天")
    public Date getFinalDay(@ApiParam("时间") @RequestParam("date") Date date) {
        return TimeUtils.getFinalDay(date);
    }

    @GetMapping("/getStartDay")
    @ApiOperation("获取某年某月第一天")
    public Date getStartDay(@ApiParam("时间") @RequestParam("date") Date date) {
        return TimeUtils.getStartDay(date);
    }

    @GetMapping("/getTimestramp")
    @ApiOperation("获取特定时间时间戳")
    public Long getTimestramp(@ApiParam("年份") @RequestParam("year") int year,
                              @ApiParam("月份") @RequestParam("month") int month,
                              @ApiParam("天") @RequestParam("day") int day,
                              @ApiParam("小时") @RequestParam("hour") int hour,
                              @ApiParam("分钟") @RequestParam("minute") int minute,
                              @ApiParam("秒") @RequestParam("second") int second) {
        return TimeUtils.getTimestramp(year, month, day, hour, minute, second);
    }

    @GetMapping("/getWeekdays")
    @ApiOperation("获取某天的星期")
    public String getWeekdays(@ApiParam("时间字符串") @RequestParam("date") String date) {
        return TimeUtils.getWeekdays(date);
    }

    @GetMapping("/getYesterdayBeginTimestamp")
    @ApiOperation("获取昨天开始时间戳")
    public Long getYesterdayBeginTimestamp() {
        return TimeUtils.getYesterdayBeginTimestamp();
    }

    @GetMapping("/judgeLeapYear")
    @ApiOperation("判断某一年是否闰年")
    public Boolean judgeLeapYear(@ApiParam("年份") @RequestParam("year") int year) {
        return TimeUtils.judgeLeapYear(year);
    }

    @GetMapping("/timeDifForDay")
    @ApiOperation("时间差计算(时:分:秒)")
    public String timeDifForDay(@ApiParam("开始时间戳") @RequestParam("startTime") Long startTime,
                                @ApiParam("结束时间戳") @RequestParam("endTime") Long endTime) {
        return TimeUtils.timeDifForDay(startTime, endTime);
    }

    @GetMapping("/timeFormat")
    @ApiOperation("将时间转换成指定格式")
    public String timeFormat(@ApiParam("时间") @RequestParam("date") Date date,
                             @ApiParam("时间格式") @RequestParam("model") String model) {
        return TimeUtils.timeFormat(date, model);
    }

    @GetMapping("/timeFormat-default")
    @ApiOperation("将时间转换成默认格式 yyyy-MM-dd HH:mm:ss")
    public String timeFormat(@ApiParam("时间") @RequestParam("date") Date date) {
        return TimeUtils.timeFormat(date);
    }

}
