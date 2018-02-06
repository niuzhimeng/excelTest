package com.nzm.service;

import com.nzm.model.po.staff;

/**
 * Created by Nzm on 2018/2/6.
 */
public interface StaffService {

    staff update(staff sta);

    staff select(String jobNo);

    staff insert(staff sta);
}
