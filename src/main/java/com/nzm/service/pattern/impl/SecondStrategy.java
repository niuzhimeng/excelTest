package com.nzm.service.pattern.impl;

import com.nzm.service.pattern.Strategy;

/**
 * Created by Nzm on 2018/2/23.
 */
public class SecondStrategy implements Strategy {
    @Override
    public String doSomething() {
        return "second";
    }
}
