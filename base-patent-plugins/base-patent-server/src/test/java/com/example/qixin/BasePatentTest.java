package com.example.qixin;

import com.example.qixin.entity.BasePatent;
import com.example.qixin.repository.BasePatentRepository;
import com.example.qixin.utils.DataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创  建   时  间： 2018/10/25 0:09
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BasePatentTest {

    @Autowired
    private ReactiveMongoTemplate template;
    @Autowired
    private BasePatentRepository repository;

    @Test //1、将trs数据存到mongo中
    public void test() throws Exception{
        String path = "D:\\datas\\daichuli\\20180504to20180525";
        String fileType = "wgzl";
        String[] strinfo = {"wgzl(38772)"};
        for(String file : strinfo){
            BufferedReader reader = DataUtils.reader(new File(path+"\\"+file+".trs"),"GBK");
            String str = null;
            Integer num = 0;
            BasePatent bean = new BasePatent();
            while ((str = reader.readLine()) != null) {
                if(str.startsWith("<REC>")){
                    ++ num;
                    if(!StringUtils.isEmpty(bean.getGkh())){
                        bean.setFileType(fileType);
                        Mono<BasePatent> patentMono = repository.save(bean);
                        System.out.println(patentMono.block().getId());
                        bean = new BasePatent();
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

    }
}
