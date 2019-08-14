package com.tangerineSpecter.javaBaseUtils.common.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import common.DocumentInfo;
import common.util.TimeUtils;

public class UtilTest {

	public static void main(String[] args) throws Exception {
		DocumentInfo.createDocInfo();
		System.out.println(TimeUtils.timeDifForYear(1554314411000L, System.currentTimeMillis()));
	}
}
