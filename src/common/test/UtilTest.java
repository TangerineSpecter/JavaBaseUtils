package common.test;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import common.DocumentInfo;
import common.util.TimeUtils;

public class UtilTest {

	public static void main(String[] args) throws Exception {
		DocumentInfo.createDocInfo();
		Long time1 = 1551492610000L;
		Long time2 = 1551507743000L;
		Long time3 = 1552272427000L;
		System.out.println(TimeUtils.timeDifForDay(1551492610000L, 1551507743000L));
		System.out.println(TimeUtils.timeFormat(new Date(), "yyyy-MM"));
		
		String json = "{\"agent\":[{\"number\":\"7567564765474\",\"state\":\"1\",\"id\":\"6851\",\"type\":\"1\"}]}";
		JSONObject object = JSON.parseObject(json);
		JSONArray jsonArray = JSON.parseArray(object.getString("agent"));
		JSONObject parseObject = JSON.parseObject(jsonArray.get(0).toString());
		System.out.println(parseObject);
		//System.out.println(jsonObject.get(0));
	}
}
