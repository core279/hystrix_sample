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


	@Test
	public void histrixTest() throws Exception{

		String [] targetList = CommonConstant.internal_targetList;
		Random random = new Random();
		int idx =  random.nextInt(targetList.length);

		switch (idx){
			case 0:
				 _hystrixService.hystrixCall1(targetList[idx]);
			case 1:
				 _hystrixService.hystrixCall2(targetList[idx]);
			case 2:
				 _hystrixService.hystrixCall3(targetList[idx]);
			case 3:
				 _hystrixService.hystrixCall4(targetList[idx]);
			case 4:
				 _hystrixService.hystrixCall5(targetList[idx]);
			case 5:
				 _hystrixService.hystrixCall6(targetList[idx]);
			case 6:
				 _hystrixService.hystrixCall7(targetList[idx]);
			case 7:
				 _hystrixService.hystrixCall8(targetList[idx]);
			case 8:
				 _hystrixService.hystrixCall9(targetList[idx]);
			case 9:
				 _hystrixService.hystrixCall10(targetList[idx]);
			default:
		}

	}

}
