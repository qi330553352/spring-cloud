package com.example.qixin;

import com.example.qixin.api.PatentDescribeApi;
import com.example.qixin.entity.PatentDescribe;
import com.example.qixin.utils.DataUtils;
import com.example.qixin.vo.PatentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasePatentServerApplicationTests {

	@Autowired
	private ReactiveMongoTemplate template;
	@Autowired
	private PatentDescribeApi patentDescribeApi;

	@Test
	public void contextLoads() {

	}

	@Test //1、将trs数据存到mongo中
	public void test() throws Exception{
		Integer year = 1985;
		String path = "D:\\datas\\6-研究中心数据\\CN_PT\\syxx_ft";
		Integer patentType = 20;
		String[] strinfo = {"syxx_ft-1994-1-34868"};
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = sdf.format(date);
		List<PatentDescribe> list = new ArrayList<>();
		for(String file : strinfo){
			BufferedReader reader = DataUtils.reader(new File(path+"\\"+file+".trs"),"GBK");
			String str = null;
			Integer num = 0;
			Integer idx = 0;
			PatentInfo bean = new PatentInfo();
			while ((str = reader.readLine()) != null) {
				if(str.startsWith("<REC>")){
					++ num;
					if(!StringUtils.isEmpty(bean.getGkh())){
						++idx;
						bean.setIdx(idx);
						bean.setYear(year);
						bean.setPatentType(patentType);
						bean.setCreateTime(createTime);

						PatentDescribe p = new PatentDescribe();
						p.setAbs(bean.getAbs());
						p.setId(bean.getSqh());
						p.setZqx(bean.getZqx());
						p.setZy(bean.getZy());
						list.add(p);
						//Mono<PatentInfo> patentMono = template.save(bean);
						//System.out.println(patentMono.block().getId());
						bean = new PatentInfo();
					}
				}else if(!StringUtils.isEmpty(str) && str.contains("=")){
					try{
						String val = str.split("=")[1];
						DataUtils.setTrsZL(val,str,bean);
					}catch (Exception e){
						System.out.println();
						System.out.println(str);
						throw new Exception(e);
					}

				}
			}
			System.out.println();
			System.out.println("num:"+num);
		}
		System.out.println(list.size());
		for (PatentDescribe bean : list) {
			try {
				Mono<PatentDescribe> entityMono = patentDescribeApi.addBean(bean);
				System.out.println(entityMono.block());
			}catch (DuplicateKeyException e){
				e.printStackTrace();
			}
		}
		/*try {
			Flux<PatentDescribe> flux = patentDescribeApi.addBeans(list);
			flux.toStream().forEach(System.out::println);
		}catch (DuplicateKeyException e){
			e.printStackTrace();
		}*/
	}
}
