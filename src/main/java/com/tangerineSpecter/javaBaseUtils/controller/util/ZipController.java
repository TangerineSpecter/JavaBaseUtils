package com.tangerinespecter.javabaseutils.controller.util;

import com.tangerinespecter.javabaseutils.common.util.ZipUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 压缩和解压工具类
 *
 * @author TangerineSpecter
 */
@Api(tags = "压缩和解压工具类")
@RestController
@RequestMapping("/zipUtils")
public class ZipController {

    @GetMapping("/compress")
    @ApiOperation(value = "压缩文件")
    public void compress(@ApiParam("源文件路径") @RequestParam("srcFilePath") String srcFilePath,
                         @ApiParam("压缩包名字") @RequestParam("destFileName") String destFileName) {
        ZipUtils.compress(srcFilePath, destFileName);
    }

    @GetMapping("/gZip")
    @ApiOperation(value = "压缩数据")
    public byte[] gZip(@ApiParam("二进制数据") @RequestParam("data") byte[] data) {
        return ZipUtils.gZip(data);
    }

    @GetMapping("/unZip")
    @ApiOperation(value = "解压数据")
    public byte[] unZip(@ApiParam("二进制数据") @RequestParam("data") byte[] data) {
        return ZipUtils.unZip(data);
    }


}
