package com.tangerinespecter.javabaseutils.controller.util;

import com.tangerinespecter.javabaseutils.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字符串工具类控制
 *
 * @author TangerineSpecter
 */
@Api(tags = "字符串工具类")
@RestController
@RequestMapping("/stringUtils")
public class StringController {

    @GetMapping("/isEmpty")
    @ApiOperation(value = "判断字符串是否为空")
    public boolean isEmpty(@ApiParam("字符串内容") @RequestParam("str") String str) {
        return StringUtils.isEmpty(str);
    }

    @GetMapping("/isNumber")
    @ApiOperation(value = "判断是否为数字")
    public boolean isNumber(@ApiParam("字符串内容") @RequestParam("number") String number) {
        return StringUtils.isNumber(number);
    }

    @GetMapping("/isAllNumber")
    @ApiOperation(value = "判断所有字符串是否都为数字")
    public boolean isAllNumber(@ApiParam("字符串集") @RequestParam("numbers") String... numbers) {
        return StringUtils.isAllNumber(numbers);
    }

    @GetMapping("/isAnyEmpty")
    @ApiOperation(value = "判断多个字符串是否有任意一个为空")
    public boolean isAnyEmpty(@ApiParam("字符串集") @RequestParam("strs") String... strs) {
        return StringUtils.isAnyEmpty(strs);
    }

    @GetMapping("/getOrderNum")
    @ApiOperation(value = "订单号生成")
    public String getOrderNum() {
        return StringUtils.getOrderNum();
    }

    @GetMapping("/getLocalHostIP")
    @ApiOperation(value = "获取本机IP地址")
    public String getLocalHostIp() {
        return StringUtils.getLocalHostIp();
    }

    @GetMapping("/randomString")
    @ApiOperation(value = "伪随机字符串（数字英文混合）")
    public String randomString(@ApiParam("字符串长度") @RequestParam("length") int length) {
        return StringUtils.randomString(length);
    }

    @GetMapping("/subString")
    @ApiOperation(value = "截取String开头指定长度的部分")
    public String subString(@ApiParam("字符串内容") @RequestParam("str") String str, @ApiParam("截取位置") @RequestParam("length") int length) {
        return StringUtils.subString(str, length);
    }
}
