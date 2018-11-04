package com.example.qixin.stream;

import com.example.qixin.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * 创  建   时  间： 2018/11/3 11:08
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Service
@EnableBinding({IReceiveService.class})
public class ReceiveService {

    @StreamListener(IReceiveService.INPUT)
    public void onReceive(Product bean){

        log.info("receive:"+ bean);
    }

}
