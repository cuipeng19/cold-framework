package com.cold.framework.notify.sms;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cuipeng
 * @date 2019/1/28 18:14
 */
public class FeigeSource implements SmsSender {

    private String account;
    private String pwd;
    private String signId;
    private String sendUrl;
    private CloseableHttpAsyncClient httpClient;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public FeigeSource() {
        this.httpClient = HttpAsyncClientBuilder.create().setMaxConnTotal(500).setMaxConnPerRoute(500).build();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    @Override
    public void sendCustomSms(String phoneNumber, String content) {
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("Account",this.account));
        formparams.add(new BasicNameValuePair("Pwd",this.pwd));
        formparams.add(new BasicNameValuePair("Content",content));
        formparams.add(new BasicNameValuePair("Mobile",phoneNumber));
        formparams.add(new BasicNameValuePair("SignId",this.signId));

        try {
            httpClient.start();

            HttpPost requestPost = new HttpPost(this.sendUrl);
            requestPost.setEntity(new UrlEncodedFormEntity(formparams,"utf-8"));

            httpClient.execute(requestPost, new FutureCallback<HttpResponse>() {
                @Override
                public void failed(Exception arg0) {
                    logger.error("Exception: " + arg0.getMessage());
                }

                @Override
                public void completed(HttpResponse arg0) {
                    logger.info("Response: " + arg0.getStatusLine());
                    try {
                        InputStream stram = arg0.getEntity().getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stram));
                        logger.info(reader.readLine());
                    } catch (Exception e) {
                        logger.error("Failed to read SMS message:",e);
                    }
                }

                @Override
                public void cancelled() {
                }
            }).get();

        } catch (Exception e) {
            logger.error("Failed to send SMS:",e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("Exception occur when connection pool closes:",e);
            }
        }));

        logger.info("Done");
    }
}
