package com.example.qixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Log4j2
@RestController
public class ProfileController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/api/profile")
    public Profile get() {
    	Profile p = new Profile();
    	p.setId("9527");
    	p.setName("rensanning");
    	p.setEmail("rensanning@gmail.com");
    	p.setRemake("此为资源服务器数据");
		log.info("返回对象为：{}",p);
        return p;
    }

}
