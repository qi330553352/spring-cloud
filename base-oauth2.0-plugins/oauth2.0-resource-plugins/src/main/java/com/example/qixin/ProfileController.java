package com.example.qixin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("/api/profile")
    public Profile get() {
    	Profile p = new Profile();
    	p.setId("9527");
    	p.setName("rensanning");
    	p.setEmail("rensanning@gmail.com");
    	p.setRemake("此为资源服务器数据");
        return p;
    }

}
