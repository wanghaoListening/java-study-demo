package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-19
 * mail:hiwanghao@didiglobal.com
 **/
public class ApiClientConfig {


    private long id;

    //机构id
    private String orgId;

    //code 用来标识机构的接口
    private String code;

    //name
    private String name;

    //参数规则
    private String paramRule;

    private String path;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamRule() {
        return paramRule;
    }

    public void setParamRule(String paramRule) {
        this.paramRule = paramRule;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
