package com.itcanteen.sponsor.utils;


import com.itcanteen.common.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 15:31
 */
public class CommonUtils {


    public static  String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    private static String[] parsePatterns={"yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"};
    public static Date parseStringToDate(String dateString ) throws AdException {
        //apache 工具类  parsePatterns为解析模式 解释yyyy-MM-dd",
        // "yyyy/MM/dd","yyyy.MM.dd 类型的字段转化成date
        try {
            return DateUtils.parseDate(dateString,parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AdException(e.getMessage());
        }
    }
}
