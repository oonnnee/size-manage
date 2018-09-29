package com.alitbit.sizeManage.util;

import com.alitbit.sizeManage.bean.CustomerInfo;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtil {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("身高", "Height");
        map.put("颈侧点-膝盖", "NeckKnee");
        map.put("1/2肩宽+袖长", "ShoulderSleeve");
        map.put("胸围", "Chest");
        map.put("腰围", "Waist");
        map.put("臀围", "Ass");
        map.put("袖肥（上臀围）", "SleeveFat");
    }

    public static Map getContrastMap(){
        Map<String, String> map = new HashMap<>();
        map.put("身高", "Height");
        map.put("颈侧点-膝盖", "NeckKnee");
        map.put("1/2肩宽+袖长", "ShoulderSleeve");
        map.put("胸围", "Chest");
        map.put("腰围", "Waist");
        map.put("臀围", "Ass");
        map.put("袖肥（上臀围）", "SleeveFat");
        return map;
    }

    public static Method getMethod(String name) throws NoSuchMethodException {
        String methodName = "get" + map.get(name);
        Class clazz = CustomerInfo.class;
        Method method = clazz.getDeclaredMethod(methodName);
        return method;
    }

    public static Map getSizeMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("S", 0);
        map.put("M", 1);
        map.put("L", 2);
        return map;
    }
}
