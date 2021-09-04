package com.tangerinespecter.javabaseutils.controller.util;

import com.tangerinespecter.javabaseutils.common.util.EasyExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * EasyExcel处理工具类
 *
 * @author TangerineSpecter
 */
@Api(tags = "EasyExcel处理工具类")
@RestController
@RequestMapping("/easyExcelUtils")
public class EasyExcelController {

    @PostMapping("/simpleRead")
    @ApiOperation(value = "获取Excel数据")
    public void simpleRead(@ApiParam("Excel路径") @RequestParam("file") MultipartFile file) throws IOException {
        EasyExcelUtils.simpleRead(file);
    }
}
