package com.exntu.hystrix;

import com.exntu.hystrix.constant.CommonConstant;
import com.exntu.hystrix.controller.TestController;
import com.exntu.hystrix.service.HystrixService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HystrixApplicationTests {

	@Autowired
	HystrixService _hystrixService;

	@Test
	public void contextLoads() throws Exception {

	}



}
