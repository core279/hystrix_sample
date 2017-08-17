package com.exntu.hystrix.controller;

import com.exntu.hystrix.constant.CommonConstant;
import com.exntu.hystrix.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

/**
 * Created by core279 on 2017. 8. 10..
 */
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    HystrixService _hystrixService;


    @Autowired
    RestTemplate _restTemplate;



    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/ribbon/backend-service")
    public String testRibbon1(){

        String targetURL = "http://backend-service/";
        return this._restTemplate.getForObject(targetURL , String.class);
    }

    @RequestMapping("/ribbon/hystrix-client")
    public String testRibbon2(){
        //local - 아님
        String targetURL = "http://hystrix-client/hystrix-client";
        return this._restTemplate.getForObject(targetURL , String.class);
    }

    @RequestMapping("/ribbon/product")
    public String testRibbon3(){
        //local
        String targetURL = "http://product/products";
        return this._restTemplate.getForObject(targetURL , String.class);
    }


    @RequestMapping(path = "{productId}", method = RequestMethod.GET)
    public String getProductInfo(@PathVariable String productId){

        return "[product id = " + productId +"]";

    }


    @GetMapping("/api")
    public Object testApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        //랜덤으로 target 설정
        Random random = new Random();
        String targetURL = "";

        //1. target 정보를 가지고 있음.
        //targetURL = CommonConstant.internal_targetList[random.nextInt(CommonConstant.internal_targetList.length)];

        //2. target을 유레카 서버에서 가져옴.
        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("backend-service");
        int randomIndex = random.nextInt(serviceInstances.size());
        targetURL = serviceInstances.get(randomIndex).getUri().toString();

        try {

            URL url = new URL(targetURL);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(1000);//1초가 넘어가면 exception 발생

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                System.out.println("NONE-Hystrix : " + targetURL + " : " + readLine);
            }
            br.close();

        } catch (MalformedURLException e) {
            System.out.println("NONE-Hystrix : " + targetURL + " : (MalformedURLException)");
            throw e;

        } catch (IOException e) {
            System.out.println("NONE-Hystrix : " + targetURL + " : (IOException)");
            throw e;
        }

        return targetURL +" : SUCCESS";

    }


    @GetMapping("/api/hystrix")
    public Object testHystrixApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        Random random = new Random();
        String targetURL = "";

        //1. target 정보를 가지고 있음.
        targetURL = CommonConstant.internal_targetList[random.nextInt(CommonConstant.internal_targetList.length)];

        //2. target 을 유레카 서버에서 가져옴.
        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("backend-service");
        int randomIndex = random.nextInt(serviceInstances.size());
        targetURL = serviceInstances.get(randomIndex).getUri().toString();

        switch (randomIndex){
            case 0:
                return _hystrixService.hystrixCall1(targetURL);
            case 1:
                return _hystrixService.hystrixCall2(targetURL);
            case 2:
                return _hystrixService.hystrixCall3(targetURL);
            case 3:
                return _hystrixService.hystrixCall4(targetURL);
            case 4:
                return _hystrixService.hystrixCall5(targetURL);
            case 5:
                return _hystrixService.hystrixCall6(targetURL);
            case 6:
                return _hystrixService.hystrixCall7(targetURL);
            case 7:
                return _hystrixService.hystrixCall8(targetURL);
            case 8:
                return _hystrixService.hystrixCall9(targetURL);
            case 9:
                return _hystrixService.hystrixCall10(targetURL);
            default:
                return  "fail";
        }

    }


    @GetMapping("/api/hystrix/ribbon")
    public Object testHystrixRibbonApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        return _hystrixService.hystrixRibbon();
    }




}
