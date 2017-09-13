package com.nzm.model.vo.enumVo;

/**
 * Created by Nzm on 2017/8/28.
 */
public enum BatchApi {

    IDENTITY("身份一致性验证"),
    VEHICLE_ILLEGAL("车辆违章"),
    BLACK_LIST("黑名单");

    private String apiName;

    BatchApi(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
