package com.liu.utils;

import com.liu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author liuliang
 * @create2021-08-2021/8/29-21:46
 * @email 2640336916@qq.com
 * @explain
 */
public class WEBUtils {

    /**
     * 从map中封装一个bean对象
     * @param clazz
     * @param parameterMap
     * @param <T>
     * @return
     */
    public static <T>  T copyParamToBean(T clazz, Map parameterMap){
        try {
            BeanUtils.populate(clazz,parameterMap);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            System.out.println("------------WEBUtils catch a NumberFormatException!-------------");
        }
        return defaultValue;
    }


}
