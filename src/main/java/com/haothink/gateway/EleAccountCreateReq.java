package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao@didiglobal.com
 **/
public class EleAccountCreateReq {

    private String mfid;
    private String registId;
    private String userName;
    private String certNo;
    private String userPhone;
    private String bankCardNo;

    private String certStartDate;
    private String certEndDate;
    /**
     * 发证机关
     */
    private String certOrg;
    private String address;
    private String applicationNo;
    private String authApplicationNo;

    public String getMfid() {
        return mfid;
    }

    public void setMfid(String mfid) {
        this.mfid = mfid;
    }

    public String getRegistId() {
        return registId;
    }

    public void setRegistId(String registId) {
        this.registId = registId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getCertStartDate() {
        return certStartDate;
    }

    public void setCertStartDate(String certStartDate) {
        this.certStartDate = certStartDate;
    }

    public String getCertEndDate() {
        return certEndDate;
    }

    public void setCertEndDate(String certEndDate) {
        this.certEndDate = certEndDate;
    }

    public String getCertOrg() {
        return certOrg;
    }

    public void setCertOrg(String certOrg) {
        this.certOrg = certOrg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getAuthApplicationNo() {
        return authApplicationNo;
    }

    public void setAuthApplicationNo(String authApplicationNo) {
        this.authApplicationNo = authApplicationNo;
    }
}
