package com.sohu.mrd.domain.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.HtmlEmail;

/**
 * @author LYG
 */
public class NetWorkProtocol {

    /**
     * �ɹ� C0000
     */
    public static final String SUCCESS = "C0000";

    /**
     * �ɹ�����
     */
    public static final String SUCCESS_MSG = "C0000";

    /**
     * ���ӵ�ַ���ɴ� C0001
     */
    public static final String EROOR_NOT_FIND = "C0001";

    /**
     * ���ӵ�ַ���ɴ�����
     */
    public static final String EROOR_NOT_FIND_MSG = "���ӵ�ַ���ɴ�";

    // -----------------------------------HTTP����ķŻز�������-----------------------------------
    /**
     * ��Ӧ��״̬Э��
     */
    public static final String RESPONSE_STATUE_PROTOCOL = "procotol";
    /**
     * ��Ӧ��״̬��
     */
    public static final String RESPONSE_STATUE_CODE = "code";
    /**
     * ��Ӧ��״̬�������
     */
    public static final String RESPONSE_STATUE_CODE_MESSAGE = "message";
    /**
     * ��Ӧ����
     */
    public static final String RESPONSE_CONTENT = "content";
    /**
     * ��Ӧ���ݵĳ���
     */
    public static final String RESPONSE_CONTENT_LENGTH = "length";
    /**
     * ��Ӧ���ݵı����ʽ
     */
    public static final String RESPONSE_CONTENT_ENCODEING = "encoding";
    /**
     * ��Ӧ���ݵ�����
     */
    public static final String RESPONSE_CONTENT_TYPE = "type";
    /**
     * ��Ӧ�ļ��Դ���
     */
    public static final String RESPONSE_ERROR = "error";
    /**
     * ��Ӧ����ϸ����
     */
    public static final String RESPONSE_ERROR_DETAIL = "error_detail";

    // -----------------------------------�ʼ���������-----------------------------------
    /**
     * ���÷��ŵ�smtp������
     */
    public static final String EMAIL_HOST_NAME = "smtp.exmail.qq.com";
    /**
     * ���÷����˵�EMAIL
     */
    public static final String EMAIL_FROM_EMAIL = "liyonggang@pezy.cn";
    /**
     * ���÷����˵�����
     */
    public static final String EMAIL_FROM_NAME = "liyonggang";
    /**
     * ���÷����˵�����
     */
    public static final String EMAIL_FROM_PWD = "";

    private NetWorkProtocol() {
    }

    /**
     * <li>ͨ��HTTPЭ�顢��GET��ʽ��������</li>
     *
     * @url ���ʵ�ַ
     * @return ����Map <li>URLCode.RESPONSE_STATUE_PROTOCOL:��Ӧ״̬Э��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE:��Ӧ״̬��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE_MESSAGE:��Ӧ״̬�������</li> <li>
     *         URLCode.RESPONSE_CONTENT_TYPE:��������</li> <li>
     *         URLCode.RESPONSE_CONTENT_ENCODEING:�����ʽ</li> <li>
     *         URLCode.RESPONSE_CONTENT_LENGTH:���ݳ���</li> <li>
     *         URLCode.RESPONSE_CONTENT:����</li> <li>
     *         ���Ӵ��󷵻� null</li>
     */
    public static Map<String, String> get(String url) {
        return get(url, "UTF-8");
    }

    /**
     * <li>ͨ��HTTPЭ�顢��GET��ʽ��������</li>
     *
     * @url ���ʵ�ַ
     * @charset �����ʽ
     * @return ����Map <li>URLCode.RESPONSE_STATUE_PROTOCOL:��Ӧ״̬Э��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE:��Ӧ״̬��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE_MESSAGE:��Ӧ״̬�������</li> <li>
     *         URLCode.RESPONSE_CONTENT_TYPE:��������</li> <li>
     *         URLCode.RESPONSE_CONTENT_ENCODEING:�����ʽ</li> <li>
     *         URLCode.RESPONSE_CONTENT_LENGTH:���ݳ���</li> <li>
     *         URLCode.RESPONSE_CONTENT:����</li> <li>
     *         ���Ӵ��󷵻� null</li>
     */
    public static Map<String, String> get(String url, String charset) {
        Map<String, String> data = new HashMap<String, String>();
        // ����Ĭ��ʵ��
        HttpClient httpClient = new DefaultHttpClient();
        // ����socket���ӳ�ʱ
        httpClient.getParams().setParameter("http.socket.timeout", 30000);
        httpClient.getParams().setParameter("http.connection.timeout", 30000);
        // Get�ύ��ʽ
        HttpGet httpGet = new HttpGet(url);
        try {
            // ִ��Get����
            HttpResponse response = httpClient.execute(httpGet);
            if (response == null) {
                return null;
            }
            setHeadData(data, response);
            response.setHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=" + charset);
            // �������ʵ��
            HttpEntity httpEntity = response.getEntity();
//			System.out.println(httpEntity.getContentEncoding());
            if (null != httpEntity) {
                setContentDate(data, httpEntity, charset);
                response = null;
                httpEntity = null;
                return data;
            }
            return null;
        } catch (ClientProtocolException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } catch (IOException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } finally {
            // �ر����ӡ��ͷ���Դ
            if (null != httpClient) {
                httpClient.getConnectionManager().shutdown();
            }
            httpGet = null;
            httpClient = null;
        }
        return data;
    }


    /**
     * <li>ͨ��HTTPЭ�顢��GET��ʽ��������</li>
     *
     *�����Զ������Զ������ļ��
     */
    public static Map<String, String> getForMonitor(String url, String charset) {
        Map<String, String> data = new HashMap<String, String>();
        // ����Ĭ��ʵ��
        HttpClient httpClient = new DefaultHttpClient();
        // ����socket���ӳ�ʱ
        httpClient.getParams().setParameter("http.socket.timeout", 15000);
        httpClient.getParams().setParameter("http.connection.timeout", 15000);
        // Get�ύ��ʽ
        HttpGet httpGet = new HttpGet(url);
        try {
            // ִ��Get����
            HttpResponse response = httpClient.execute(httpGet);
            if (response == null) {
                return null;
            }
            setHeadData(data, response);
            response.setHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=" + charset);
            // �������ʵ��
            HttpEntity httpEntity = response.getEntity();
//			System.out.println(httpEntity.getContentEncoding());
            if (null != httpEntity) {
                setContentDate(data, httpEntity, charset);
                response = null;
                httpEntity = null;
                return data;
            }
            return null;
        } catch (ClientProtocolException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } catch (IOException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } finally {
            // �ر����ӡ��ͷ���Դ
            if (null != httpClient) {
                httpClient.getConnectionManager().shutdown();
            }
            httpGet = null;
            httpClient = null;
        }
        return data;
    }



    /**
     * @param url
     *            ���ʵ����ӵ�ַ
     * @param kv
     *            ����
     * @charset �����ʽ
     * @return ����Map <li>URLCode.RESPONSE_STATUE_PROTOCOL:��Ӧ״̬Э��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE:��Ӧ״̬��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE_MESSAGE:��Ӧ״̬�������</li> <li>
     *         URLCode.RESPONSE_CONTENT_TYPE:��������</li> <li>
     *         URLCode.RESPONSE_CONTENT_ENCODEING:�����ʽ</li> <li>
     *         URLCode.RESPONSE_CONTENT_LENGTH:���ݳ���</li> <li>
     *         URLCode.RESPONSE_CONTENT:����</li> <li>
     *         ���Ӵ��󷵻� null</li>
     */
    public static Map<String, String> post(String url, Map<String, String> kv,
                                           String charset) {
        HttpClient httpClient = new DefaultHttpClient();
        // socket���ӳ�ʱ����
        httpClient.getParams().setParameter("http.socket.timeout", 30000);
        httpClient.getParams().setParameter("http.connection.timeout", 30000);
        // POST�ύ��ʽ
        HttpPost httpPost = new HttpPost(url);
        // �����б�
        List<NameValuePair> fromParams = new ArrayList<NameValuePair>();
        // ��Ӿ������
        if (null != kv) {
            for (Entry<String, String> entry : kv.entrySet()) {
                fromParams.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
        }
        // ���ò����б�ı����ʽ
        UrlEncodedFormEntity uefEntity = null;
        Map<String, String> data = new HashMap<String, String>();
        try {
            uefEntity = new UrlEncodedFormEntity(fromParams, charset);
            // ��POST��������������ʵ��
            httpPost.setEntity(uefEntity);
            // ִ��POST����
            HttpResponse response = httpClient.execute(httpPost);
            if (response == null) {
                return null;
            }
            setHeadData(data, response);
            // �������ʵ��
            HttpEntity httpEntity = response.getEntity();
            if (null != httpEntity) {
                setContentDate(data, httpEntity, charset);
                response = null;
                httpEntity = null;
                return data;
            }
        } catch (ClientProtocolException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } catch (IOException e) {
            // e.printStackTrace();
            setErrorStatue(data, e);
//			System.err.println(e.getMessage());
        } finally {
            if (null != httpClient) {
                httpClient.getConnectionManager().shutdown();
            }
            httpPost = null;
            httpClient = null;
        }
        return data;
    }

    /**
     * @param url
     *            ���ʵ����ӵ�ַ
     * @param kv
     *            ����
     * @return ����Map <li>URLCode.RESPONSE_STATUE_PROTOCOL:��Ӧ״̬Э��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE:��Ӧ״̬��</li> <li>
     *         URLCode.RESPONSE_STATUE_CODE_MESSAGE:��Ӧ״̬�������</li> <li>
     *         URLCode.RESPONSE_CONTENT_TYPE:��������</li> <li>
     *         URLCode.RESPONSE_CONTENT_ENCODEING:�����ʽ</li> <li>
     *         URLCode.RESPONSE_CONTENT_LENGTH:���ݳ���</li> <li>
     *         URLCode.RESPONSE_CONTENT:����</li> <li>
     *         ���Ӵ��󷵻� null</li>
     */
    public static Map<String, String> post(String url, Map<String, String> kv) {
        return post(url, kv, "UTF-8");
    }

    // ˽�з�����ֻ���ڲ����ã�������Ӧ״̬ͷ
    private static void setHeadData(Map<String, String> data,
                                    HttpResponse response) {
        data.put(RESPONSE_STATUE_PROTOCOL, response.getStatusLine()
                .getProtocolVersion().toString());
        data.put(RESPONSE_STATUE_CODE,
                String.valueOf(response.getStatusLine().getStatusCode()));
        data.put(RESPONSE_STATUE_CODE_MESSAGE, response.getStatusLine()
                .getReasonPhrase());
    }

    // ˽�з�����ֻ���ڲ����ã�������Ӧ������
    private static void setContentDate(Map<String, String> data,
                                       HttpEntity httpEntity, String charset)
            throws IllegalStateException, IOException {
        data.put(RESPONSE_CONTENT_TYPE,
                httpEntity.getContentType() == null ? "" : httpEntity
                        .getContentType().toString());
        data.put(RESPONSE_CONTENT_ENCODEING,
                httpEntity.getContentEncoding() == null ? "" : httpEntity
                        .getContentEncoding().toString());
        data.put(RESPONSE_CONTENT_LENGTH,
                String.valueOf(httpEntity.getContentLength()));
        InputStream inputStream = httpEntity.getContent();
		/*
		 * �����Ӧ�����ݵ��� �����绷���£���Ҫʹ��inputStream��
		 * avaliable()���������������»᷵��0������ͨ��httpEntity .getContentLength()��������ݳ���
		 */
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
                inputStream, charset));
        StringBuffer readLine = new StringBuffer();
        String tmp = "";
        while (null != (tmp = bufferRead.readLine())) {
            readLine.append(tmp);
        }
        // String content = readLine.toString();
        // content = new String(content.getBytes(data
        // .get(URLCode.RESPONSE_CONTENT_ENCODEING)), "utf-8");
        data.put(RESPONSE_CONTENT, readLine.toString());
        inputStream = null;
        bufferRead = null;
    }

    // ����http����ʱ�Ĵ�����Ϣ
    private static void setErrorStatue(Map<String, String> data, Exception e) {
        data.put(RESPONSE_STATUE_CODE, EROOR_NOT_FIND);
        data.put(RESPONSE_STATUE_CODE_MESSAGE, EROOR_NOT_FIND_MSG);
        // data.put(RESPONSE_CONTENT, e.getMessage());
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer, true));
        data.put(RESPONSE_ERROR, e.getMessage());
        data.put(RESPONSE_ERROR_DETAIL, writer.toString());
    }

    /**
     * ����һ��email �˷����������÷����ĸ���״̬
     *
     * @param host
     *            ���ŵ�smtp������
     * @param to
     *            �ռ����˺�
     * @param toName
     *            �ռ���
     * @param fromMail
     *            �������˺�
     * @param fromName
     *            ������
     * @param password
     *            ����
     * @param subject
     *            ����
     * @param body
     *            ����
     * @throws EmailException
     *             �ʼ������쳣
     */
    // public static void email(String host, String to, String toName,
    // String from, String fromName, String password, String subject,
    // String body) throws EmailException {
    // HtmlEmail email = new HtmlEmail();
    // // ���÷��ŵ�smtp������
    // email.setHostName(host);
    // email.setSubject(subject);
    // // ���smtp��������Ҫ��֤�Ļ��������������ʺš�����
    // email.setAuthentication(from, password);
    // // �����ʼ����ĺ��ַ�����
    // // �����ռ����ʺź��ռ���
    // email.addTo(to, toName);
    // // ���÷��ŵ��ʼ��ʺźͷ�����
    // email.setFrom(from, fromName);
    // // �����ʼ�����
    // email.setHtmlMsg(body);
    // email.send();
    // }

    /**
     * ����һ��email �˷����ṩһ��������
     *
     * @param to
     *            �ռ����˺�
     * @param toName
     *            �ռ���
     * @param subject
     *            ����
     * @param body
     *            ����
     * @throws EmailException
     *             �ʼ������쳣
     */
    // public static void email(String to, String toName, String subject,
    // String body) throws EmailException {
    // HtmlEmail email = new HtmlEmail();
    // // ���÷��ŵ�smtp������
    // email.setHostName(EMAIL_HOST_NAME);
    // email.setSubject(subject);
    // // ���smtp��������Ҫ��֤�Ļ��������������ʺš�����
    // email.setAuthentication(EMAIL_FROM_EMAIL, EMAIL_FROM_PWD);
    // // �����ʼ����ĺ��ַ�����
    // // �����ռ����ʺź��ռ���
    // email.addTo(to, toName);
    // // ���÷��ŵ��ʼ��ʺźͷ�����
    // email.setFrom(EMAIL_FROM_EMAIL, EMAIL_FROM_NAME);
    // // �����ʼ�����
    // email.setHtmlMsg(body);
    // email.send();
    // }
}
