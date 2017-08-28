package com.nzm.model.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Nzm on 2017/8/3.
 */
public class BatchVo {
    /**
     * 账号
     */
    private String account;
    /**
     * 签名
     */
    private String signature;
    /**
     * 接口类型
     */
    private String batchType;
    /**
     * 测试数据excel 2007
     */
    private MultipartFile excel;

    public BatchVo() {
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public MultipartFile getExcel() {
        return excel;
    }

    public void setExcel(MultipartFile excel) {
        this.excel = excel;
    }
}
