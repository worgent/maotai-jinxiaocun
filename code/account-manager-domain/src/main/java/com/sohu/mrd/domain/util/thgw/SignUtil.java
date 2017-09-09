package com.sohu.mrd.domain.util.thgw;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Describe:加密工具类
 * User: doushihui
 * Date: 2013-8-9
 * Time: 上午11:43:01
 */
public class SignUtil {
    private final static Logger log = Logger.getLogger(SignUtil.class);

    public static String buildSign(String signStr) {
        //签名
        return MD5Util.md5Hex(signStr);
    }
    public static String buildSign(StringBuffer sb, String key) {
        StringBuilder s = new StringBuilder(sb);
        s.append(key);
        log.debug("签名前串：" + s);
        //签名
        String sign = buildSign(s.toString());
        log.debug("签名后串：" + sign);
        return sign;
    }
    public static String getPrepareFillQuerySign(String[] paramArr, String key) {
        return getPrepareFillQuerySign(paramArr,key,true);
    }
    public static String getPrepareFillQuerySign(String[] paramArr, String key,boolean sort) {
        // 参数按照字母升序排序
        if (sort) {
            Arrays.sort(paramArr);
        }
        StringBuffer sb = new StringBuffer("");
        for (String p : paramArr) {
            sb.append(p);
        }
        System.out.println(sb.toString());
        String s = buildSign(sb, key);
        return s;
    }
    /**
     * 获取签名字符串，
     * @param paramArr 参数对，只包括需要加密的内容
     * @param gap 间隔，如：a=b&c=d 间隔为&
     * @param key
     * @param keyPrefix KEY前缀如：&safe=key,大多数是这个参数是空的
     * @param sort 是否排序
     * @return
     */
    public static String getSignStr(List<String> params,String gap, String keyPrefix, String key,boolean sort) {
        String[] paramArr = (String[])params.toArray(new String[0]);
        // 参数按照字母升序排序
        if (sort) {
            Arrays.sort(paramArr);
        }
        StringBuffer sb = new StringBuffer("");
        boolean first = true;
        for (String p : paramArr) {
            if (first) {
                first = false;
            } else {
                sb.append(gap);
            }
            sb.append(p);
        }
        sb.append(keyPrefix);
        String s = buildSign(sb, key);
        return s;
    }

    public static String getTelecomPrepareFillQuerySign(String[] paramArr, String key) {
        // 参数按照字母升序排序
        Arrays.sort(paramArr);
        StringBuffer sb = new StringBuffer("");
        for (String p : paramArr) {
            if( p.toLowerCase().indexOf("version")== -1 ){
                sb.append(p);
            }
        }
        String s = buildSign(sb, key);
        return s;
    }

    public static void main(String[] args) {
/*		String key = "123456";
		StringBuffer sb = new StringBuffer(
				"card_num1fill_amount100.0fill_mobile13788835072fill_type0isp_id1partner1003province_id14timestamp2012132102241338version1.0");
		String[] paramArr = new String[9];
		paramArr[0] = "is_successF";
		paramArr[1] = "partner1003";
		paramArr[2] = "timestamp201213210224133";
		paramArr[3] = "version1.0";
		paramArr[4] = "fill_time20090707122322";
		paramArr[5] = "fill_state1";
		//paramArr[7] = "card_num1";//预查询
		paramArr[6] = "succ_amount20";
		paramArr[7] = "request_order_no1111111";
		paramArr[8] = "agent_order_no221111111";
		System.out.println(getPrepareFillQuerySign(paramArr, "123456"));
		System.out.println(buildSign(sb, key));*/
        StringBuffer sb = new StringBuffer();
        sb.append("agentid=jike&source=esales&merchantKey=70mf2vlpew1j6q12eusch2l26z86ketxhzugvln9phzt2l6yut630i8kjbn4qmok02xjgvsdrsxhilkzzyxis3tauad6lvf76agab0ugk8brh1szzmtrc9svr55di2wr");
        StringBuffer sb1 = new StringBuffer();
        sb1.append("agentid=test_agent_id_1&source=esales&merchantKey=111111");
        System.out.println(buildSign(sb.toString()));
    }

}
