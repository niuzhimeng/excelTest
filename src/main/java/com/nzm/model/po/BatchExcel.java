package com.nzm.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BatchExcel {
    private String id;

    private String fileName;

    private String extName;

    private String path;

    private String createdTime;

    private String belongAccount;
}