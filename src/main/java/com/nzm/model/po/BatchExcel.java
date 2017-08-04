package com.nzm.model.po;

public class BatchExcel {
    private String id;

    private String fileName;

    private String extName;

    private String path;

    private String createdTime;

    private String belongAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName == null ? null : extName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getBelongAccount() {
        return belongAccount;
    }

    public void setBelongAccount(String belongAccount) {
        this.belongAccount = belongAccount == null ? null : belongAccount.trim();
    }
}