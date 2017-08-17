package com.exntu.hystrix.service;

import com.exntu.hystrix.constant.CommonConstant;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
 * Created by core279 on 2017. 8. 14..
 */
@Service
public class HystrixService {

    @Autowired
    RestTemplate _restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;




    //////////////////////////////////////////////////////////////
    //////  Hystrix
    //////////////////////////////////////////////////////////////

    @HystrixCommand(fallbackMethod = "fallbackHystrix",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "600"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //회로를 트립할지 여부.
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //10초 동안 실패 가능 수  ex) 2개 까지는 회로를 트립하지 않는다.
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //회로를 트립(open) 후 close 하기전. 요청거부 시간 10초
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall(String target) throws Exception {

        return network(target);

    }




    //////////////////////////////////////////////////////////////
    //////  Hystrix + Ribbon
    //////////////////////////////////////////////////////////////

    @HystrixCommand(fallbackMethod = "fallbackHystrixRibbon",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "600"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //회로를 트립할지 여부.
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //10초 동안 실패 가능 수  ex) 2개 까지는 회로를 트립하지 않는다.
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //회로를 트립(open) 후 close 하기전. 요청거부 시간 10초
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "400"),
                    @HystrixProperty(name = "maxQueueSize", value = "1000"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "400"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixRibbon() throws Exception {

        //System.out.println("hystrixRibbon");
        String targetURL = "http://" + CommonConstant.backend_application_name + "/";
        return targetURL + " : " + this._restTemplate.getForObject(targetURL , String.class);
    }










    //////////////////////////////////////////////////////////////
    //////  fallbackMethod
    //////////////////////////////////////////////////////////////

    public Object fallbackHystrix(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackHystrix]");
        return "redirect : SUCCESS";
    }

    public Object fallbackHystrixRibbon() throws Exception {
        System.out.println( "Hystrix + Ribbon : [fallbackHystrixRibbon]");
        return "redirect : SUCCESS";
    }












    //////////////////////////////////////////////////////////////
    //////  통신
    //////////////////////////////////////////////////////////////

    public String network(String targetURL) throws Exception{

        //통신
        try {

            URL url = new URL( targetURL);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(1000);//1초가 넘어가면 exception 발생

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            while ((readLine = br.readLine()) != null) {
                //System.out.println("Hystrix : " + targetURL + " : " + readLine);
            }
            br.close();
        } catch (MalformedURLException e) {
            //System.out.println("Hystrix : " + targetURL + " : (MalformedURLException) : [RE Direct]");
            throw e;
        } catch (HystrixRuntimeException e) {
            //System.out.println( "Hystrix : " + targetURL + " : (HystrixRuntimeException) : [RE Direct]");
            throw e;
        } catch (IOException e) {
            //System.out.println( "Hystrix : " + targetURL + " : (IOException) : [RE Direct]" );
            throw e;
        }
        return targetURL +" : SUCCESS";

    }



    //////////////////////////////////////////////////////////////
    //////  reDirect
    //////////////////////////////////////////////////////////////

    public Object reDirect() throws Exception{

        String [] targetList = CommonConstant.internal_targetList;
        Random random = new Random();
        String targetURL = "";

        //1. target 정보를 가지고 있음.
        targetURL = CommonConstant.internal_targetList[random.nextInt(CommonConstant.internal_targetList.length)];

        //2. target 을 유레카 서버에서 가져옴.
        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("backend-service");
        int randomIndex = random.nextInt(serviceInstances.size());
        targetURL = serviceInstances.get(randomIndex).getUri().toString();

        return this.hystrixCall(targetURL);
    }


}
