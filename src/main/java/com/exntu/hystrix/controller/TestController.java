package com.exntu.hystrix.controller;

import com.exntu.hystrix.constant.CommonConstant;
import com.exntu.hystrix.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * Created by core279 on 2017. 8. 10..
 */
@RestController
public class TestController {


    @Autowired
    HystrixService _hystrixService;


    @GetMapping("/api")
    public Object testApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        //랜덤으로 target 설정
        Random random = new Random();
        String targetURL = CommonConstant.internal_targetList[random.nextInt(CommonConstant.internal_targetList.length)];


        //통신
        try {

            URL url = new URL("http://"+ targetURL);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(1000);//1초가 넘어가면 exception 발생

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
//                System.out.println("NONE-Hystrix : " + targetURL + " : " + readLine);
            }
            br.close();

        } catch (MalformedURLException e) {
//            System.out.println("NONE-Hystrix : " + targetURL + " : (MalformedURLException)");
            throw e;

        } catch (IOException e) {
//            System.out.println("NONE-Hystrix : " + targetURL + " : (IOException)");
            throw e;
        }

        return targetURL +" : SUCCESS";

    }


    @GetMapping("/api/hystrix")
    public Object testHystrixApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        String [] targetList = CommonConstant.internal_targetList;
        Random random = new Random();


        int idx =  random.nextInt(targetList.length);

        switch (idx){
            case 0:
                return _hystrixService.hystrixCall1(targetList[idx]);
            case 1:
                return _hystrixService.hystrixCall2(targetList[idx]);
            case 2:
                return _hystrixService.hystrixCall3(targetList[idx]);
            case 3:
                return _hystrixService.hystrixCall4(targetList[idx]);
            case 4:
                return _hystrixService.hystrixCall5(targetList[idx]);
            case 5:
                return _hystrixService.hystrixCall6(targetList[idx]);
            case 6:
                return _hystrixService.hystrixCall7(targetList[idx]);
            case 7:
                return _hystrixService.hystrixCall8(targetList[idx]);
            case 8:
                return _hystrixService.hystrixCall9(targetList[idx]);
            case 9:
                return _hystrixService.hystrixCall10(targetList[idx]);
            default:
                return  "fail";
        }

    }




}
