package com.exntu.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;
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
@Component
public class TestController {

    private String[] internal_targetList = {
            "172.27.0.121:8511"
            ,"172.27.0.121:8512"
            ,"172.27.0.121:8513"
            ,"172.27.0.121:8514"
            ,"172.27.0.121:8515"
            ,"172.27.0.121:8516"
            ,"172.27.0.121:8517"
            ,"172.27.0.121:8518"
            ,"172.27.0.121:8519"
            ,"172.27.0.121:8520"
    };

    private String[] external_targetList = {
            "14.63.212.248:1301"
            ,"14.63.212.248:1302"
            ,"14.63.212.248:1303"
            ,"14.63.212.248:1304"
            ,"14.63.212.248:1305"
            ,"14.63.212.248:1306"
            ,"14.63.212.248:1307"
            ,"14.63.212.248:1308"
            ,"14.63.212.248:1309"
            ,"14.63.212.248:1310"
    };

    @GetMapping("/api")
    public Object testApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        //랜덤으로 target 설정
        Random random = new Random();
        String targetURL = internal_targetList[random.nextInt(internal_targetList.length)];


        //통신
        try {

            URL url = new URL("http://"+ targetURL);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            System.out.print("NONE-Hystrix : " + targetURL + " : ");
            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
            }
            br.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return targetURL +" : FAIL";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return targetURL +" : FAIL";
        }


        return targetURL +" : SUCCESS";
    }


    @GetMapping("/api/hystrix")
    @HystrixCommand(fallbackMethod = "redirectTestApi")
    public Object testHystrixApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        //랜덤으로 target 설정
        Random random = new Random();
        String targetURL = internal_targetList[random.nextInt(internal_targetList.length)];


        //통신
        try {

            URL url = new URL("http://"+ targetURL);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream is = connection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String readLine = null;

            System.out.print( "Hystrix : " + targetURL + " : ");
            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
            }
            br.close();
        } catch (MalformedURLException e) {
            System.out.println("Hystrix(MalformedURLException) : " + targetURL);
        } catch (HystrixRuntimeException e) {
            System.out.println("Hystrix(HystrixRuntimeException) : " + targetURL);
        } catch (IOException e) {
            System.out.println("Hystrix(IOException) : " + targetURL);
        }

        return targetURL +" : SUCCESS";
    }

    @GetMapping("/api/redirect")
    public Object redirectTestApi(
            HttpServletRequest request,
            HttpServletResponse response ) throws Exception {

        System.out.println(" ======[RE Direct]===== : ");

        return "redirect : SUCCESS";
    }


}
