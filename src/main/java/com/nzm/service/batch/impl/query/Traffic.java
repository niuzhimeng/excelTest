package com.nzm.service.batch.impl.query;

import com.nzm.model.po.BatchExcel;
import com.nzm.service.batch.PoiTest;
import org.springframework.web.multipart.MultipartFile;

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
    public List<BatchExcel> write(List<String> resultList, String account, MultipartFile excel) throws Exception {
        return null;
    }

    @Override
    public String appendUrl(List<String> cell) {
        return url + "&carNumber=" + cell.get(0) + "&carCode=" + cell.get(1) + "&carDriveNumber=" + cell.get(2);
    }

    public void appendAccountInfo(String account, String token) {
        url = super.fatherAppendAccount(url, account, token);
    }
}
