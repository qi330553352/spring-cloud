package com.example.qixin.base.file.server;

import com.obs.services.ObsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

// https://blog.csdn.net/layman1024/article/details/78273451 图片处理
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseFileServerApplicationTests {

	@Test
	public void contextLoads() throws IOException {
		String endPoint = "obs.cn-south-1.myhwclouds.com";
		String ak = "GBVFDI7CRUPU7FXFLBYT";
		String sk = "CcHSSvbMk1qZnbSWFGQLqZIxfggB8o4SOH9alKYO";
		// 创建ObsClient实例
		ObsClient obsClient = new ObsClient(ak, sk, endPoint);
		//Thumbnails.of("D:\\log\\123.jpg").size(200, 300).toFile("D:\\log\\1234.jpg");
		File file = new File("D:\\log\\123.jpg");
		File file4 = new File("D:\\log\\1234.png");
//		InputStream inputStream = PictureFactory.instance(file).watermark(200,300,Positions.BOTTOM_RIGHT,ImageIO.read(file4),0.5f);
//		System.out.println();
//		System.out.println();
		// 使用访问OBS
		//obsClient.putObject("static-resource-dev", "event/test.jpg",inputStream);
		// 关闭obsClient
		obsClient.close();
	}

}
