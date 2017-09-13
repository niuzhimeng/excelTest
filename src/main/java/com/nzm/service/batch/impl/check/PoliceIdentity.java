package com.nzm.service.batch.impl.check;

import com.nzm.model.po.BatchExcel;
import com.nzm.service.batch.PoiTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 身份证一致性
 * Created by Nzm on 2017/8/2.
 */
public class PoliceIdentity extends PoiTest {

    /**
     * 访问url
     */
    private String url = "http://localhost:8080/tianXingDataApi/rest/police/identity";

    @Override
    public List<BatchExcel> write(List<String> resultList, String account, MultipartFile excel) throws Exception {
        return null;
    }

    public void appendAccountInfo(String account, String token) {
        url = super.fatherAppendAccount(url, account, token);
    }


    @Override
    public String appendUrl(List<String> cell) {
        return url + "&name=" + cell.get(0) + "&idCard=" + cell.get(1);
    }
}
