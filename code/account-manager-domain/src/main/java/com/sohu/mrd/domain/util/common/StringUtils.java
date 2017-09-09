package com.sohu.mrd.domain.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Describe:
 * User: BjYangKai
 * Date: 2010-6-3
 * Time: 11:29:33
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * ȥ���ַ���ǰ��ȫ�ǺͰ�ǿո�
     *
     * @param tarStr
     * @return
     */
    public static String trimSbc(String tarStr) {
        if (isEmpty(tarStr)) return tarStr;
        tarStr = tarStr.trim();
        while (tarStr.startsWith("��")) {
            tarStr = tarStr.substring(1, tarStr.length()).trim();
        }
        while (tarStr.endsWith("��")) {
            tarStr = tarStr.substring(0, tarStr.length() - 1).trim();
        }
        return tarStr;
    }


    /**
     * Method change ...
     * <p/>
     * ȥ���ı��е�������js�ű���<object>,<frame><iframe>
     *
     * @param htmlStr of type String
     * @return String
     */
    public static String change(String htmlStr) {
        if(StringUtils.isBlank(htmlStr)){
            return htmlStr;
        }

        String temp = htmlStr;
        String temp2 = "";
        String regEx_a = "(<\\s*?(a|A)\\s*?>?[\\s\\S]*?<?[\\s]*?\\/[\\s]*?(a|A)[\\s]*?>)";
//       String regEx_img="(<\\s*img\\s+([^>]*)(\\/)?\\s*>)";
//       String regEx_src="(src\\s*=\\s*(\"|'|)http://\\w+\\.360buy\\.com[\\s\\S]*?)";
        String regEx_href = "((href|HREF)\\s*=\\s*(\"|'|)(http://)?\\w+\\.360buy\\.com[\\s\\S]*?)";
//       String regEx_href_content="(href=[\\\"\\'](http:\\/\\/|\\.\\/|\\/)?\\w+(\\.\\w+)*(\\/\\w+(\\.\\w+)?)*(\\/|\\?\\w*=\\w*(&\\w*=\\w*)*)?(#\\w*)?[\\\"\\'])";
//       String regEx_href_content = "(href=[\\\"\\'](http:\\/\\/|\\.\\/|\\/)?[\\s\\S]*?[\\\"\\'])";
        String regEx_href_content = "(href=(\"(http:\\/\\/|\\.\\/|\\/)?[\\s\\S]*?\")|href=(\'(http:\\/\\/|\\.\\/|\\/)?[\\s\\S]*?\'))";
        String regEx_script = "(<[\\s]*?(script|SCRIPT)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(script|SCRIPT)[\\s]*?>)";
        String regEx_object = "(<[\\s]*?(object|OBJECT)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(object|OBJECT)[\\s]*?>)";
        String regEx_frame = "(<[\\s]*?(frame|FRAME)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(frame|FRAME)[\\s]*?>)";
        String regEx_iframe = "(<[\\s]*?(iframe|IFRAME)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(iframe|IFRAME)[\\s]*?>)";
        String regEx_form = "(<[\\s]*?\\/?[\\s]*?form[^>]*?>)";
        String regEx_input = "(<[\\s]*?\\/?[\\s]*?input[^>]*?>)";
        String regEx_textarea = "(<[\\s]*?\\/?[\\s]*?textarea[^>]*?>)";
        String regEx_select = "(<[\\s]*?\\/?[\\s]*?select[^>]*?>)";
        String regEx_option = "(<[\\s]*?\\/?[\\s]*?option[^>]*?>)";
        String regEx_button = "(<[\\s]*?\\/?[\\s]*?button[^>]*?>)";
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(temp);
        temp = m_script.replaceAll(" "); //����script��ǩ ����""
        Pattern p_object = Pattern.compile(regEx_object, Pattern.CASE_INSENSITIVE);
        Matcher m_object = p_object.matcher(temp);
        temp = m_object.replaceAll(" "); //����object��ǩ
        Pattern p_frame = Pattern.compile(regEx_frame, Pattern.CASE_INSENSITIVE);
        Matcher m_frame = p_frame.matcher(temp);
        temp = m_frame.replaceAll(" "); //����frame��ǩ
        Pattern p_iframe = Pattern.compile(regEx_iframe, Pattern.CASE_INSENSITIVE);
        Matcher m_iframe = p_iframe.matcher(temp);
        temp = m_iframe.replaceAll(" "); //����iframe��ǩ
        Pattern p_a = Pattern.compile(regEx_a, Pattern.CASE_INSENSITIVE);
        Matcher m_a = p_a.matcher(temp);
        Pattern p_form = Pattern.compile(regEx_form, Pattern.CASE_INSENSITIVE);
        Matcher m_form = p_form.matcher(temp);
        temp = m_form.replaceAll(" ");
        Pattern p_input = Pattern.compile(regEx_input, Pattern.CASE_INSENSITIVE);
        Matcher m_input = p_input.matcher(temp);
        temp = m_input.replaceAll(" ");
        Pattern p_textarea = Pattern.compile(regEx_textarea, Pattern.CASE_INSENSITIVE);
        Matcher m_textarea = p_textarea.matcher(temp);
        temp = m_textarea.replaceAll(" ");
        Pattern p_select = Pattern.compile(regEx_select, Pattern.CASE_INSENSITIVE);
        Matcher m_select = p_select.matcher(temp);
        temp = m_select.replaceAll(" ");
        Pattern p_option = Pattern.compile(regEx_option, Pattern.CASE_INSENSITIVE);
        Matcher m_option = p_option.matcher(temp);
        temp = m_option.replaceAll(" ");
        Pattern p_button = Pattern.compile(regEx_button, Pattern.CASE_INSENSITIVE);
        Matcher m_button = p_button.matcher(temp);
        temp = m_button.replaceAll(" ");
        temp2 = temp;
        Pattern p_href = Pattern.compile(regEx_href, Pattern.CASE_INSENSITIVE);
        while (m_a.find()) {
            String sb = null;
            sb = m_a.group(0);
            Matcher m_href = p_href.matcher(sb);
            if (!m_href.find()) {
                //�滻��Ҫ����������
                Pattern p_content = Pattern.compile(regEx_href_content, Pattern.CASE_INSENSITIVE);
                Matcher m_content = p_content.matcher(sb);
                while (m_content.find()) {      //ֻ�滻href������
                    String change = null;
                    change = m_content.group(0);
                    temp2 = stringReplace(temp2, change, "href=\"#\"");
                }
            }
        }

//       Pattern p_img=Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
//       Matcher m_img=p_img.matcher(temp);                             // ����ͼƬ
//       Pattern p_src=Pattern.compile(regEx_src,Pattern.CASE_INSENSITIVE);
//       while(m_img.find()){
//           String sb=null;
//           sb=m_img.group(0);
//           Matcher m_src=p_src.matcher(sb);
//           if(m_src.find()){
//               temp=stringReplace(temp,sb,"##");
//           }
//       }
        return temp2;
    }


    /**
     * Method stringReplace ...
     * �滻�ַ���
     *
     * @param sourceString    of type String
     * @param toReplaceString of type String
     * @param replaceString   of type String
     * @return String
     */
    public static String stringReplace(String sourceString, String toReplaceString, String replaceString) {
        String returnString = sourceString;
        int stringLength = 0;
        if (toReplaceString != null) {
            stringLength = toReplaceString.length();
        }
        if (returnString != null && returnString.length() >= stringLength) {
            int max = 0;
            String S4 = " ";
            for (int i = 0; i < sourceString.length(); i++) {
                max = i + toReplaceString.length() > sourceString.length() ? sourceString.length() : i + stringLength;
                String S3 = sourceString.substring(i, max);
                if (!S3.equals(toReplaceString)) {
                    S4 += S3.substring(0, 1);
                } else {
                    S4 += replaceString;
                    i += stringLength - 1;
                }
            }
            returnString = S4;
        }
        return returnString;
    }

    public static void main(String args[]) {
        String testStr = "<div class=\"mc\">\n" +
                "\t\t\t\t\t<div id=\"miaozhen7899\" class=\"da da180x348\"></div><a href=\"http://g.miaozhen.com/r.gif?^k=389^p=0sI2^o=http://search.360buy.com/Search?keyword=����3518\" target=\"_blank\"><img height=\"348\" width=\"180\" border=\"0\" src=\"http://misc.360buyimg.com/da/digi/di_m_49.jpg\"></a></div>\n" +
                "\t\t\t\t\t<ul class=\"list-h\">\n" +
                "                        <li class=\"fore\">\n" +
                "<div class=\"p-img\"><a href=\"http://www.sdfgasdf.com/product/#177130\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"�����֣�PHILIPS��X630 GSM�ֻ�(�ſ��)\" src=\"http://img10.360buyimg.com/n4/3646/f1168036-8be9-4da7-9ba0-1815d987d316.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.asdfffsdf.com/product/177130.html\" target=\"_blank\">�����֣�PHILIPS��X630 GSM�ֻ�(�ſ��)<font color=\"#ff6600\">�50�����������ֱ���������β���������ؼ۳�����</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//P1E80AA226ADFA798009E03A04E4491C7,2.png\"></strong></div>\n" +
                "</li>\n<frame>asdfasdfasdfasdfasdf</frame>" +
                "<li class=\"fore\">\n" +
                "<div class=\"p-img\"><a href=\"http://www.360buy.com/product/182872.html\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"���ǣ�SAMSUNG��S3650C GSM �ֻ�����ɫ��\" src=\"http://img10.360buyimg.com/n4/3574/084e657c-ba8c-4145-909b-5fd7ef7e2f85.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.360buy.com/product/182872.html\" target=\"_blank\">���ǣ�SAMSUNG��S3650C GSM �ֻ�����ɫ��<font color=\"#ff6600\">���ܾ�����������ļ۸�֧��У��������̨QQ! �ͣ�ר����Ĥ+�����ʿ���ֻ���+������أ�</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//P23581EF99C3028E874F68094CDB4F6D9,2.png\"></strong></div>\n" +
                "</li>\n<object>ooooooooooooo</object>" +
                "<li class=\"fore\">\n" +
                "<div class=\"p-img\"><a href=\"http://www.360buy.com/product/215061.html\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"ŵ����5320DI  ˫������ͷ 3G���������ֻ� �Ƕ��ƻ�\" src=\"http://img10.360buyimg.com/n4/2965/f66562ff-1ca8-445b-a121-74fffa210fb3.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.360buy.com/product/215061.html\" target=\"_blank\">ŵ����5320DI  ˫������ͷ 3G���������ֻ� �Ƕ��ƻ�<font color=\"#ff6600\">˫������ͷ�����ܻ������֧��8G�洢��չ��</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//P30BEA0CF663E5687B85EC5CF78FD4069,2.png\"></strong></div>\n" +
                "</li>\n<IFRAME>iiiiiiiiiiiiiiiii</IFRAME> " +
                "<li>\n" +
                "<div class=\"p-img\"><a href=\"http://www.360buy.com/product/156891.html\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"Ħ��������Moto Rola��A1210 GSM�ֻ� ����ɫ��\" src=\"http://img10.360buyimg.com/n4/4852/e73f429b-fb27-493e-8b40-6518ba6172a4.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.360buy.com/product/156891.html\" target=\"_blank\">Ħ��������Moto Rola��A1210 GSM�ֻ� ����ɫ��<font color=\"#ff6600\">�۸���˵�λ����50Ԫ��ȯ����49Ԫ2G���������Ͳ�ͣ</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//P9168FD62AE82A7E718797FBEB589D323,2.png\"></strong></div>\n" +
                "</li>\n<script src=\"http://price.360buy.com/ows/script/iplocation.js?t=20100601\" type=\"text/javascript\"></script>" +
                "<li>\n" +
                "<div class=\"p-img\"><a href=\"www.baidu.com\">�ٶ�</a><a href=\"http://www.360buy.com/product/212176.html\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"����(SAMSUNG) I6500U 3G �ֻ������ף�WCDMA/GSM\" src=\"http://img10.360buyimg.com/n4/4998/b071df80-3ff0-45f0-a72f-6b7cbc6168e6.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.360buy.com/product/212176.html\" target=\"_blank\">����(SAMSUNG) I6500U 3G �ֻ������ף�WCDMA/GSM<font color=\"#ff6600\">��100��ȯ+���籭�������ӣ�andoid����ϵͳ����������!</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//P7293AA5389DD83C8AC8601CDFFDDA89A,2.png\"></strong></div>\n" +
                "</li>\n" +
                "<li>\n" +
                "<img height=\"100\" width=\"100\" alt=\"�����֣�PHILIPS��X630 GSM�ֻ�(�ſ��)\" src=\"http://img10.360buyimg.com/n4/3646/f1168036-8be9-4da7-9ba0-1815d987d316.jpg\">" +
                "<div class=\"p-img\"><a href=\"http://www.360buy.com/product/213235.html\" target=\"_blank\"><img height=\"100\" width=\"100\" alt=\"Ħ��������MOTOROLA��ME501(��ɫ)  GSM/WCDMA �Ƕ����ֻ�\" src=\"http://img10.360buyimg.com/n4/4128/1bf6efee-d85c-4d9a-b457-41c964796687.jpg\"></a></div>\n" +
                "<div class=\"p-name\"><a href=\"http://www.360buy.com/product/213235.html\" target=\"_blank\">Ħ��������MOTOROLA��ME501(��ɫ)  GSM/WCDMA �Ƕ����ֻ�<font color=\"#ff6600\">���αر���GPS����ϵͳ������Ѹ����������״֧��WIFI</font></a></div>\n" +
                "<div class=\"p-price\">�����ۣ�<strong><img src=\"http://price.360buy.com//PA2B78827BC037F745210AF33457280D7,2.png\"></strong></div>\n" +
                "</li>\n" +
                "\n" +
                "<div style=\"MARGIN-TOP: 0.5em; DISPLAY: block; FONT-SIZE: 13px; FONT-WEIGHT: bold\"><a href=\"http://www.google.com.hk/aclk?sa=L&ai=Chg3dV42QTKyAI4HmuAPZxs2qAr2a_cQBjZCmqhPB2ZzZExABIMFUUNmzsrf6_____wFgnQGqBERP0N3JpXysxToA8u69gpXUHKVD1R01W5cez0I1cWxlPbEjB3FnpRVMXTPw8wblR_a_M92FcXxTc-28ZUDMPUWO2wq6vg&num=1&sig=AGiWqtweuXcmh6Uq8C_-GzD6pa4mcew8rQ&adurl=http://www.google.com.hk/webhp%3Fhl%3Dzh-CN%26sourceid%3Dcnhpbmtxt\"><span style=\"color:#1111cc;\">��&nbsp;Google&nbsp;�����ղ�</span></a></div><br /><br /></div>\n" +
                "</div>\n" +
                "<div id=\"als\">Google.com.hk ʹ���������ԣ� <a href=\"http://www.google.com.hk/setprefs?sig=0_lPVDmNVUOqgrdcZq8HlD75ICfsQ=&hl=zh-TW\"><span style=\"color:#1111cc;\">���ģ����w��</span></a> <a href=\"http://www.google.com.hk/setprefs?sig=0_lPVDmNVUOqgrdcZq8HlD75ICfsQ=&hl=en\"><span style=\"color:#1111cc;\">English</span></a><br /><br /></div></div>\n" +
                "<div id=\"res\"><span style=\"color:#1111cc;\"></span></div><span id=\"footer\">\n" +
                "</span><center style=\"FILTER: alpha(opacity=100)\" id=\"fctr\" zoom=\"1\">\n" +
                "<div style=\"FONT-SIZE: 10pt\">\n" +
                "<div style=\"TEXT-ALIGN: center; MARGIN: 19px auto\" id=\"fll\"><a href=\"http://www.google.com.hk/intl/zh-CN/ads/\"><span style=\"color:#1111cc;\">����Ӫ���ƻ�</span></a><a href=\"http://www.google.com.hk/intl/zh-CN/about.html\"><span style=\"color:#1111cc;\">Google ��ȫ</span></a><a href=\"#\"><span style=\"color:#1111cc;\">Google.com in English</span></a></div></div>\n" +
                "\n" +
                "<p style=\"COLOR: #767676; FONT-SIZE: 8pt\">&copy; 2010 - <a href=\"http://www.google.com.hk/intl/zh-CN/privacy.html\"><span style=\"color:#1111cc;\">��˽Ȩ����</span></a></p></center>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "\t\t\t\t\t</ul>\n" +
                "\t\t\t\t</div><script type=\"text/javascript\">new Image().src=\"http://forum.csdn.net/Common/TopicRead.ashx?postDate=2010-05-25+21%3a32%3a34&topicID=641f1bbd-0ec7-40c0-ac69-15a4c18a92ea&\"+(new Date().getTime().toString(36));new Image().src=\"http://forum.csdn.net/PointForum/SetLatestVisitedForum.ashx?sectionid=a3049f56-b572-48f5-89be-4797b70d71cd</script><form action=\"category_updateCategory.action\" method=\"post\" id=\"updateCategory\" style=\"display:none;\">\n" +
                "     <input type=\"hidden\" name=\"category.status\" id=\"status\"/>\n" +
                "     <input type=\"hidden\" name=\"category.id\" id=\"id\"/>\n" +
                "     <input type=\"hidden\" name=\"category.fid\" id=\"fid\"/>\n" +
                "     <input type=\"hidden\" name=\"category.indexId\" id=\"indexId\"/>\n" +
                "     <input type=\"hidden\" name=\"category.lev\" id=\"lev\"/>\n" +
                "     <input type=\"hidden\" name=\"page\" value=\"$!page\"/>\n" +
                "</form>";
        System.out.println(change(testStr));
    }

}
