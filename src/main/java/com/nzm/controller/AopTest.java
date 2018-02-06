package com.nzm.controller;

import com.nzm.service.Jedis.JedisClient;
import com.nzm.model.po.staff;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.AopTestService;
import com.nzm.service.StaffService;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import javax.annotation.Resource;

/**
 * Created by Nzm on 2017/12/27.
 */
@RestController
@RequestMapping(value = "redisTest")
public class AopTest {

    @Resource
    private AopTestService aopTest1;

    @Resource
    private StaffService staffService;

//    @Resource(name = "JedisClient")
//    private JedisClient jedisClient;

    @RequestMapping(value = "methodTest", method = RequestMethod.GET)
    public JsonResponse methodTest(@RequestParam String name) {
        return new JsonResponse<>().createSuccess(aopTest1.test(name));
    }

    @RequestMapping(value = "redis", method = RequestMethod.POST)
    public JsonResponse test(@RequestBody staff sta) {
        staffService.update(sta);

        //jedisClient.set("1","2222");
        return new JsonResponse<>().createSuccess(sta);
    }
}
