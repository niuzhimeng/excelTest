package com.nzm.controller.strategy;

import com.nzm.model.vo.JsonResponse;
import com.nzm.service.pattern.Strategy;
import com.nzm.service.pattern.impl.FirstStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Observable;

/**
 * Created by Nzm on 2018/2/23.
 */
@RestController
@RequestMapping(value = "parten")
public class PatternController {
    /**
     * 策略模式
     *
     * @return
     */
    @RequestMapping(value = "strategy", method = RequestMethod.GET)
    public JsonResponse strategy(@RequestParam String name) throws Exception {
        System.out.println(FirstStrategy.class);
        Class<?> name1 = Class.forName("com.nzm.service.pattern.impl." + name);
        Strategy o = (Strategy) name1.newInstance();
        String s = o.doSomething();
        return new JsonResponse<>().createSuccess(s);
    }
}
