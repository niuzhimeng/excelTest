package com.nzm.controller;

import com.nzm.model.po.staff;
import com.nzm.model.vo.JsonResponse;
import com.nzm.service.AopTestService;
import com.nzm.service.StaffService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResponse update(@RequestBody staff sta) {
        staffService.update(sta);
        return new JsonResponse<>().createSuccess(sta);
    }

    /**
     * 从缓存查找
     *
     * @param jobNo 工号
     * @return
     */
    @RequestMapping(value = "selectByCache", method = RequestMethod.GET)
    public JsonResponse selectByCache(@RequestParam String jobNo) {
        return new JsonResponse<>().createSuccess(staffService.selectByCache(jobNo));
    }

    /**
     * 普通查找
     *
     * @param jobNo 工号
     * @return
     */
    @RequestMapping(value = "select", method = RequestMethod.GET)
    public JsonResponse select(@RequestParam String jobNo) {
        return new JsonResponse<>().createSuccess(staffService.select(jobNo));
    }

    /**
     * 插入查找
     *
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public JsonResponse insert(@RequestBody staff sta) {
        return new JsonResponse<>().createSuccess(staffService.insert(sta));
    }
}
