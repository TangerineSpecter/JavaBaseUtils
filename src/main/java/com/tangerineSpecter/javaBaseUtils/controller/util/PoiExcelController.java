package com.tangerinespecter.javabaseutils.controller.util;

import com.tangerinespecter.javabaseutils.common.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * PoiExcel处理工具类
 *
 * @author TangerineSpecter
 */
@Api(tags = "PoiExcel处理工具类")
@RestController
@RequestMapping("/poiExcelUtils")
public class PoiExcelController {

    @GetMapping("/getExcel")
    @ApiOperation(value = "获取Excel数据")
    public void getExcel(@ApiParam("Excel路径") @RequestParam("filePath") String filePath) {
        ExcelUtils.getExcel(filePath);
    }

    @GetMapping("/createExcel")
    @ApiOperation(value = "创建Excel")
    public void createExcel(@ApiParam("表头") @RequestParam("tableHead") String[] tableHead,
                            @ApiParam("数据列表") @RequestParam("dataList") List<String[]> dataList,
                            @ApiParam("true：生成xlsx，false：生成xls格式") @RequestParam("isExcel") boolean isExcel) {
        ExcelUtils.createExcel(tableHead, dataList, isExcel);
    }

}
