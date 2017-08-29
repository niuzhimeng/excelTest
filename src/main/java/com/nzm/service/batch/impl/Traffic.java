package com.nzm.service.batch.impl;


import com.nzm.service.batch.PoiTest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆违章
 * Created by Nzm on 2017/8/2.
 */
public class Traffic extends PoiTest {

    /**
     * 访问url
     */
    private String url = "http://localhost:8080/tianXingDataApi/rest/traffic/offences";

    @Override
    public String appendUrl(List<String> cell) {
        if(cell.size() != 3){
            return null;
        }
        return url + "&carNumber=" + cell.get(0) + "&carCode=" + cell.get(1) + "&carDriveNumber=" + cell.get(2);
    }

    public void appendAccountInfo(String account, String token) {
        url = super.fatherAppendAccount(url, account, token);
    }
}
