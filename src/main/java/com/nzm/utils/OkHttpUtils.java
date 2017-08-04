package com.nzm.utils;

import com.squareup.okhttp.*;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * OkHttp工具类
 * Created by Nzm on 2017/8/3.
 */
public class OkHttpUtils {

    private static Logger LOG = Logger.getLogger(OkHttpUtils.class);
    private static OkHttpClient client = new OkHttpClient();
    private static OkHttpClient clientNoLimit = new OkHttpClient();//没有超时时间限制
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType STRING = MediaType.parse("text/x-markdown; charset=utf-8");

    static {
        //超时配置
        client.setConnectTimeout(300, TimeUnit.SECONDS);
        client.setReadTimeout(300, TimeUnit.SECONDS);
    }

    /**
     * 普通的get方法
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        LOG.info("请求URL 为: ==>" + url);
        return getMethod(url);
    }

    private static String getMethod(String url) {
        Long start = System.currentTimeMillis();
        String result = null;
        Request request = new Request.Builder().url(url).build();
        try {
            result = client.newCall(request).execute().body().string();
            Response response = client.newCall(request).execute();
            if (response.code() == 401) {
                return null;
            }
        } catch (IOException e) {
            LOG.error("调用异常:url:" + url);
            e.printStackTrace();
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * POST请求，json格式
     *
     * @param url  访问URL
     * @param json 传送的json串
     * @return 返回的数据
     */
    public static String post(String url, String json) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            result = response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }


    /**
     * put方法（提交json串和headers）
     *
     * @param url
     * @param json
     * @param headers
     * @return
     */
    public static String postJsonHeader(String url, String json, Map<String, String> headers) {
        String result = "";
        Long start = System.currentTimeMillis();
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = null;
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                builder.header(entity.getKey(), entity.getValue());
            }
            request = builder.url(url).put(formBody).build();
            Response response;
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

    /**
     * 访问https证书验证
     *
     * @return
     * @throws Exception
     */
    public static OkHttpClient getConnection() throws Exception {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }}, new SecureRandom());
            client.setSslSocketFactory(ctx.getSocketFactory());
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }

    public static String post(String url) {
        Long start = System.currentTimeMillis();
        String result = "";
        try {
            RequestBody formBody = new FormEncodingBuilder().build();
            Request request = new Request.Builder().url(url).post(formBody).build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                //System.out.println(result);
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("调用异常:url:" + url);
        } finally {
            Long end = System.currentTimeMillis();
            LOG.info("开始时间：" + start + " 结束时间：" + end + " 此次调用时长为" + (end - start));
        }
        return result;
    }

}
