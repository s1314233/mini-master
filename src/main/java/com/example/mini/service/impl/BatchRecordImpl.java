package com.example.mini.service.impl;

import com.example.mini.service.BatchRecordService;
import sun.tools.jar.resources.jar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class BatchRecordImpl implements BatchRecordService {
//    hippo地址
    private final String hippoUrl = "";
    private final String cmd =
            "java -jar D:\\jacoco-0.8.10\\lib\\jacococli.jar dump --address 192.168.64.156 --port 6301 --destfile dump\\interface_%test_dev_key%.exec --reset\n" +
            "java -jar D:\\jacoco-0.8.10\\lib\\jacococli.jar dump --address 192.168.64.154 --port 6301 --destfile dump\\fm_%test_dev_key%.exec --reset\n" +
            "java -jar D:\\jacoco-0.8.10\\lib\\jacococli.jar dump --address 192.168.64.155 --port 6301 --destfile dump\\inner_%test_dev_key%.exec --reset\n" +
            "java -jar D:\\jacoco-0.8.10\\lib\\jacococli.jar dump --address 192.168.64.153 --port 6301 --destfile dump\\wxasfi_%test_dev_key%.exec --reset";

    @Override
    public void batchRecord(String testCases) throws IOException {
        String[] testCaseArray = testCases.split(",");
        initLog(testCaseArray);
        for(String testCase : testCaseArray){
            String testUrl = hippoUrl + testCase;
            URL url = new URL("https://www.baidu.com/") ;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int reponse = connection.getResponseCode();
            String text = connection.getResponseMessage();
            System.out.println("响应状态码为："+reponse+"\tmessage为："+text);
            try {
                Process process = Runtime.getRuntime()
                        .exec(cmd);
                process.waitFor();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        covRecord();
    }

    public  void covRecord(){

    }

    public void saveLog(String testCase,String text){

    }

    public void initLog(String[] testCaseArray){

    }
}
