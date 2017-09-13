package com.nzm.utils;

import com.nzm.model.vo.enumVo.BatchApi;
import com.nzm.service.batch.impl.check.PoliceIdentity;
import com.nzm.service.batch.impl.check.Traffic;
import com.nzm.service.batch.impl.check.BlackList;

/**
 * Created by Nzm on 2017/8/28.
 */
public class ApiClassFactory {

    public static Class<?> getConcrete(String batchApi) {
        Class<?> threadClazz = null;
        if (batchApi.equals(BatchApi.IDENTITY.toString())) {
            threadClazz = PoliceIdentity.class;
        } else if (batchApi.equals(BatchApi.VEHICLE_ILLEGAL.toString())) {
            threadClazz = Traffic.class;
        } else if (batchApi.equals(BatchApi.BLACK_LIST.toString())) {
            threadClazz = BlackList.class;
        }
        return threadClazz;
    }

}
