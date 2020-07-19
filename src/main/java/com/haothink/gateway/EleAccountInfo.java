package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao@didiglobal.com
 **/
public class EleAccountInfo {

    private String userName;
    private String certNo;
    private String bankUserId;
    private String eleAccountNo;
    private long balance;
    private String userPhone;
    private boolean idPhotoFlag;
    private String bankCardNo;
    private long controlAmount;


    //Y:新客户 N:老客户 U:未知
    private String newUserFlag = "U";


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

    public String getBankUserId() {
        return bankUserId;
    }

    public void setBankUserId(String bankUserId) {
        this.bankUserId = bankUserId;
    }

    public String getEleAccountNo() {
        return eleAccountNo;
    }

    public void setEleAccountNo(String eleAccountNo) {
        this.eleAccountNo = eleAccountNo;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public boolean isIdPhotoFlag() {
        return idPhotoFlag;
    }

    public void setIdPhotoFlag(boolean idPhotoFlag) {
        this.idPhotoFlag = idPhotoFlag;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public long getControlAmount() {
        return controlAmount;
    }

    public void setControlAmount(long controlAmount) {
        this.controlAmount = controlAmount;
    }

    public String getNewUserFlag() {
        return newUserFlag;
    }

    public void setNewUserFlag(String newUserFlag) {
        this.newUserFlag = newUserFlag;
    }
}
