package com.nzm.model.enums;

/**
 * 反馈枚举类
 * Created by nzm on 2017/6/1.
 */
public enum FeedBack {

    LOGIN_FAILED("用户名或密码错误"),
    TWICE_PWD_DIFFERENT("两次输入密码不一致"),
    OLD_PWD_FAILED("旧密码输入错误"),
    UPDATE_PASSWORD_SUCCESS("密码修改成功,请重新登录"),
    SUCCESS("登录成功"),
    LOGOUT("注销成功"),
    BASIC_INFO_DEPARTMENT_ISNULL("请先填写部门基础信息表"),
    ACCOUNT_OR_PASSWORD_LACK("账号或密码为空"),
    ACCOUNT_PASSWORD_NOT_MATCH("账号或密码错误"),
    COMPANY_DEPARTMENT_RECORD_SUCCESS("公司规模信息录入成功"),
    COMPANY_BUSINESS_INFO_RECORD_SUCCESS("公司商务信息录入成功"),
    COMPANY_INFO_RECORD_SUCCESS("公司信息录入成功"),
    RISK_DEPARTMENT_INFO_RECORD_SUCCESS("风控部门信息录入成功"),
    COLLECTION_DEPARTMENT_INFO_RECORD_SUCCESS("催收信息录入成功"),
    INDUSTRY_COMMERCE_INFO_RECORD_SUCCESS("公司工商信息录入成功"),
    FILE_UPLOAD_SUCCESS("文件上传成功"),
    FILE_UPDATE_SUCCESS("文件修改成功"),
    COMPANY_INFO_UPDATE_SUCCESS("公司信息修改成功"),
    COMPANY_SIZE_UPDATE_SUCCESS("公司规模信息修改成功"),
    COMPANY_COLLECTION_DEPARTMENT_INFO_UPDATE_SUCCESS("公司贷后部门信息修改成功"),
    COMPANY_RISK_DEPARTMENT_INFO_UPDATE_SUCCESS("公司风控部门信息修改成功"),
    INDUSTRY_COMMERCE_INFO_UPDATE_SUCCESS("公司工商信息修改成功"),
    COMPANY_PERSON_INFO_RECORD_SUCCESS("公司人员信息录入成功"),
    COMPANY_PERSON_INFO_UPDATE_SUCCESS("公司人员信息修改成功"),
    BUSINESS_FINANCE_INFO_RECORD_SUCCESS("公司财物信息录入成功"),
    BUSINESS_FINANCE_INFO_UPDATE_SUCCESS("公司财物信息修改成功"),
    BASIC_FINANCE_INFO_RECORD_SUCCESS("公司融资信息录入成功"),
    BASIC_FINANCE_INFO_UPDATE_SUCCESS("公司融资信息修改成功"),
    COMPANY_AFTER_LOAN_INFO_RECORD_SUCCESS("公司贷后信息录入成功"),
    COMPANY_AFTER_LOAN_INFO_UPDATE_SUCCESS("公司贷后信息修改成功"),
    ORGANIZATIONAL_STRUCTURE_RECORD_SUCCESS("组织架构信息录入成功"),
    ORGANIZATIONAL_STRUCTURE_UPDATE_SUCCESS("组织架构信息修改成功"),
    FINANCE_RECORD_SUCCESS("财务信息录入成功"),
    FINANCE_UPDATE_SUCCESS("财务信息修改成功"),
    FINANCING_RECORD_SUCCESS("融资信息录入成功"),
    FINANCING_UPDATE_SUCCESS("融资信息修改成功"),
    RISK_RECORD_SUCCESS("风控信息录入成功"),
    RISK_UPDATE_SUCCESS("风控信息修改成功"),
    GET_CUSTOMER_CHANNEL_RECORD_SUCCESS("获客渠道信息录入成功"),
    GET_CUSTOMER_CHANNEL_UPDATE_SUCCESS("获客渠道信息更新成功"),
    LOAN_FUNDS_RECORD_SUCCESS("放款资金录入成功"),
    LOAN_FUNDS_UPDATE_SUCCESS("放款资金修改成功"),
    ASSETS_INFO_RECORD_SUCCESS("公司业务类型录入成功"),
    ASSETS_INFO_UPDATE_SUCCESS("公司业务类型修改成功"),
    ASSETS_INTRODUCTION_RECORD_SUCCESS("公司资产信息录入成功"),
    ASSETS_INTRODUCTION_UPDATE_SUCCESS("公司资产信息更新成功"),
    AUTHORIZATION_RECORD_SUCCESS("授权成功"),
    PREVIEW_REPORT_SUBMIT_SUCCESS("报告提交成功"),
    APPROVAL_HISTORY_RECORD_SUCCESS("操作成功"),
    PUSH_REPORT_FAIL("推送报告失败"),
    PUSH_REPORT_SUCCESS("推送报告成功"),

    GLOBAL_EXCEPTION("出错了！请联系系统管理员");

    private String status;

    FeedBack(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
