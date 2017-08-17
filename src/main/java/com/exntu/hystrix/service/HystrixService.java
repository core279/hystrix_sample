package com.exntu.hystrix.service;

import com.exntu.hystrix.constant.CommonConstant;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by core279 on 2017. 8. 14..
 */
@Service
public class HystrixService {

    @Autowired
    RestTemplate _restTemplate;

    //Hystrix + Ribbon
    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),//큐의 동적 변경
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixRibbon() throws Exception {

        String targetURL = "http://" + CommonConstant.backend_application_name + "/";
        return targetURL + " : " + this._restTemplate.getForObject(targetURL , String.class);
    }




    @HystrixCommand(fallbackMethod = "fallbackMethod1",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),//큐의 동적 변경
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
        )

    public Object hystrixCall1(String target) throws Exception {

        return network(target);

    }


    @HystrixCommand(fallbackMethod = "fallbackMethod2",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall2(String target) throws Exception {

        return network(target);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod3",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall3(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod4",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall4(String target) throws Exception {

        return network(target);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod5",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall5(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod6",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall6(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod7",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall7(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod8",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall8(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod9",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //circuit 동작 여부.
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //최소요청갯수
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"), // 에러비율이 10%가 넘으면 circuit 발생
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //한번 circuit이 open되면 10초간 호출이 차단되며, 10초 경과후 단 1개의 호출을 허용하며 여전히 실패하면 open이 10초 연장된다
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall9(String target) throws Exception {

        return network(target);
    }


    @HystrixCommand(fallbackMethod = "fallbackMethod10",
            commandProperties = {
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),//해당 메소드에 허용되는 최대 요청 수
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),//호출 제한 시간 - 2초가 넘으면 fallbackMethod 호출
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //회로를 트립할지 여부.
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //10초 동안 실패 가능 수  ex) 2개 까지는 회로를 트립하지 않는다.
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") //회로를 트립(open) 후 close 하기전. 요청거부 시간 10초
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "101"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "60"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
    )
    public Object hystrixCall10(String target) throws Exception {

        return network(target);
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
                System.out.println("Hystrix : " + targetURL + " : " + readLine);
            }
            br.close();
        } catch (MalformedURLException e) {
            System.out.println("Hystrix : " + targetURL + " : (MalformedURLException) : [RE Direct]");
            throw e;
        } catch (HystrixRuntimeException e) {
            System.out.println( "Hystrix : " + targetURL + " : (HystrixRuntimeException) : [RE Direct]");
            throw e;
        } catch (IOException e) {
            System.out.println( "Hystrix : " + targetURL + " : (IOException) : [RE Direct]" );
            throw e;
        }
        return targetURL +" : SUCCESS";

    }



    //////////////////////////////////////////////////////////////
    //////  fallbackMethod
    //////////////////////////////////////////////////////////////

    public Object fallbackMethod() throws Exception {
        System.out.println( "Hystrix + Ribbon : [fallbackMethod]");
        return "redirect : SUCCESS";
    }


    public Object fallbackMethod1(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod1]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod2(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod2]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod3(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod3]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod4(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod4]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod5(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod5]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod6(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod6]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod7(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod7]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod8(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod8]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod9(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod9]");
        return "redirect : SUCCESS";
    }

    public Object fallbackMethod10(String targetURL) throws Exception {
        System.out.println( "Hystrix : " + targetURL + " : [fallbackMethod10]");
        return "redirect : SUCCESS";
    }


    public Object reDirect() throws Exception{

        String [] targetList = CommonConstant.internal_targetList;
        Random random = new Random();


        int idx =  random.nextInt(targetList.length - 2);
        System.out.println( "fallback : " + targetList[idx] + " : ====================================");
        switch (idx){
            case 0:
                return this.hystrixCall1(targetList[idx]);
            case 1:
                return this.hystrixCall2(targetList[idx]);
            case 2:
                return this.hystrixCall3(targetList[idx]);
            case 3:
                return this.hystrixCall4(targetList[idx]);
            case 4:
                return this.hystrixCall5(targetList[idx]);
            case 5:
                return this.hystrixCall6(targetList[idx]);
            case 6:
                return this.hystrixCall7(targetList[idx]);
            case 7:
                return this.hystrixCall8(targetList[idx]);
            default:
                return  "fail";
        }
    }









//    @HystrixCommand(fallbackMethod = "fallbackMethod10",
//            commandProperties = {
//                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "50"),//해당 메소드에 허용되는 최대 요청 수
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000"),//호출 제한 시간 - 2초가 넘으면 fallbackMethod 호출
//                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //회로를 트립할지 여부.
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"), //10초 동안 실패 가능 수  ex) 10개 까지는 회로를 트립하지 않는다.
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000") //회로를 트립(open) 후 close 하기전. 요청거부 시간 20초
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"), // 20% 백분율로 회로를 트립 할지 결정 이후 폴백 노리로 단락요청을 시작
//            },
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "30"),
//                    @HystrixProperty(name = "maxQueueSize", value = "101"),
//                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "30"),
//                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") }
//    )

}
