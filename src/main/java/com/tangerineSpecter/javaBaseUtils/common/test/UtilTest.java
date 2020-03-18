package com.tangerinespecter.javabaseutils.common.test;

import com.google.common.collect.HashMultimap;
import com.tangerinespecter.javabaseutils.common.DocumentInfo;
import org.apache.commons.collections.MultiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author TangerineSpecter
 */
public class UtilTest {

    public static void main(String[] args) throws Exception {
        //DocumentInfo.createDocInfo();
        List<Integer> ids = new ArrayList<>();
        int a = 10;
        for (int i = 1; i < 48; i++) {
            ids.add(i);
        }
        int temp = ids.size() / a + 1;
        for (int i = 0; i < temp; i++) {
            if (i == temp - 1) {
                System.out.println(ids.subList(a * i, ids.size()));
            } else {
                System.out.println(ids.subList(a * i, a * (i + 1)));
            }
        }
        System.out.println(ids);
    }
}
