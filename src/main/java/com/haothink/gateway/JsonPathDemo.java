package com.haothink.gateway;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by wanghao on 2020-07-17
 * mail:hiwanghao
 **/
public class JsonPathDemo {




    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {


        EleAccountCreateReq eleAccountCreateReq = new EleAccountCreateReq();
        eleAccountCreateReq.setUserName("helloword");
        eleAccountCreateReq.setBankCardNo("123456");

        Map<String,String> keyValues= BeanUtils.describe(eleAccountCreateReq);

        //响应配置
        Properties properties = new Properties();
        properties.put("variation.userName","{'path':'$.document.body.TransStatusDesc','type':'日期'}");
        properties.put("variation.bankCardNo","{'path':'$.document.head.HeadMsgVersion','type':'金融'}");

        properties.put("constant.headTranCode","{'path':'$.document.head.HeadTranCode','value':'IBPS_FSM_140002'}");

        properties.put("default.mac","{'path':'$.document.head.mac','value':'0A:32:AA:75:81:1D'}");

        JSONObject requestDate = new JSONObject();
        for(String keyName : properties.stringPropertyNames()) {
            String[] keyList = keyName.split("\\.");

            String json = properties.getProperty(keyName);
            FiledDataDefination filedDataDefination = JSONObject.parseObject(json, FiledDataDefination.class);
            if("variation".equals(keyList[0])) {

                Object value = keyValues.get(keyList[1]);
                JSONPath.set(requestDate, filedDataDefination.getPath(), value);
            }else if("constant".equals(keyList[0])){
                JSONPath.set(requestDate, filedDataDefination.getPath(), filedDataDefination.getValue());
            }else {
                Object value = keyValues.get(keyList[1]);
                JSONPath.set(requestDate, filedDataDefination.getPath(), Objects.nonNull(value)?value:filedDataDefination.getValue());
            }
        }

        System.out.println("http post : "+requestDate.toString());
    }


    public static class EleAccountCreateReq {
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
    };


    public static class FiledDataDefination {

        private String path;

        private String type;

        private String dateFormat;

        private String value;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
