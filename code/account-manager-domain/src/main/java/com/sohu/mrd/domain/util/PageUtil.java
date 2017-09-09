package com.sohu.mrd.domain.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Describe:开始起至页
 * User: doushihui
 * Date: 2013-10-21
 * Time: 下午1:41:09
 */
public class PageUtil {
    /**
     *
     * @param startIndex 开始页
     * @param endIndex  结束页
     * @param pageSize  每行数
     * @return
     */
    public static Map<String,Integer> getPageStartOrEndRows(int startIndex,int endIndex,int pageSize){
        Map<String,Integer> map = new HashMap<String,Integer>();
        if(startIndex<=0){
            startIndex=1;
        }
        if(startIndex > endIndex){
            int tempIndex = 0;
            tempIndex = startIndex;
            startIndex=endIndex;
            endIndex = tempIndex;
        }
        map.put("startRow",(startIndex-1)*pageSize);
        map.put("endRow",(endIndex-startIndex+1)*pageSize);
        return map;
    }
}
