package com.haothink.gateway;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haothink.gateway.ex.ParamExpression;
import com.haothink.utils.DateUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import org.apache.commons.jexl3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghao on 2020-07-19
 * mail:hiwanghao@didiglobal.com
 **/
public class ParamRule {

    protected static final Logger logger = LoggerFactory
            .getLogger(ParamRule.class);
    private static final String PARAM_KEY_NAME = "param";
    private static final String JSON_PARAM_KEY_NAME = "jsonParam";
    private static final String CONTENT_TYPE_KEY = "contentType";
    private static final String HEADER_KEY_NAME = "headerParam";

    private boolean isHttps = false;
    private String method = "get";
    private HashMap<String, ParamExpression> paramRuleMap;
    private HashMap<String, ParamExpression> headerRuleMap;
    private Template jsonBodyTempl;
    private String contentType;



    public ParamRule() {
    }


    public Map<String, String> evaluateTool(final HashMap<String, Object> ctxMap, HashMap<String, ParamExpression> ruleMap) {
        if (ruleMap == null || ruleMap.isEmpty()) {
            return new HashMap<String, String>();
        }
        final Map<String, String> evaluatedMap = new HashMap<String, String>();
        final JexlContext jc = new MapContext();
        jc.set("$p", ctxMap);
        jc.set("$", ParamRule.class);
        jc.set("timestampSec", nowUnixTime());
        jc.set("currentTimeMillis", nowTime());
        for (final Map.Entry<String, ParamExpression> en :ruleMap.entrySet()) {
            final String name = en.getKey();
            final ParamExpression paramExpression = en.getValue();
            final String vStr = paramExpression.eval(jc);
            evaluatedMap.put(name, vStr);
            //logger.info("key====" + name + ",value======" + vStr);
        }
        return evaluatedMap;
    }
    public Map<String, String> eval(final HashMap<String, Object> ctxMap) {

        return evaluateTool(ctxMap,this.paramRuleMap);
    }
    public Map<String, String> evalHeader(HashMap<String, Object> ctxMap) {
        return evaluateTool(ctxMap,headerRuleMap);
    }

    public boolean isHttps() {

        return this.isHttps;
    }

    public boolean isPost() {
        return "post".equalsIgnoreCase(this.method);
    }

    public boolean isPostJson() {
        return "postJson".equalsIgnoreCase(this.method);
    }

    public void mergeAndOverride(final String jsonTxt) {

        if (StringUtils.isNotBlank(jsonTxt)) {
            final JSONObject jsonObject = JSONObject.parseObject(jsonTxt);

            if (jsonObject.containsKey("https")) {
                this.isHttps = jsonObject.getBooleanValue("https");
            }

            final String methodStr = jsonObject.getString("method");
            if (StringUtils.isNotBlank(methodStr)) {
                this.method = methodStr;
            }

            if (null == this.paramRuleMap) {
                this.paramRuleMap = new HashMap<String, ParamExpression>();
            }

            if (jsonObject.containsKey(PARAM_KEY_NAME)) {
                final JSONArray paramArray = jsonObject.getJSONArray(PARAM_KEY_NAME);
                for (final Object object : paramArray) {
                    final String str = (String) object;
                    final String[] strs = str.split("=");
                    String rule = strs[1];
                    ParamExpression paramExpression = new ParamExpression(rule);

                    this.paramRuleMap.put(strs[0], paramExpression);
                }
            }

            if (jsonObject.containsKey(JSON_PARAM_KEY_NAME)) {
                //如果是post json 的方式，则参数规则字段是一个json 字符串
                final Configuration cfg = new Configuration();
                cfg.setNumberFormat("#");
                try {
                    this.jsonBodyTempl = new Template("jsonBodyTempl", new StringReader(jsonObject.getString(JSON_PARAM_KEY_NAME)), cfg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (jsonObject.containsKey(CONTENT_TYPE_KEY)) {
                this.contentType = jsonObject.getString(CONTENT_TYPE_KEY);
            }
            if (null == this.headerRuleMap) {
                this.headerRuleMap = new HashMap<>();
            }
            if (jsonObject.containsKey(HEADER_KEY_NAME)) {
                final JSONObject jsonObj = JSONObject.parseObject(jsonObject.getString(HEADER_KEY_NAME));
                for (final Map.Entry<String, Object> entry : jsonObj.entrySet()) {
                    String rule = (String)entry.getValue();
                    ParamExpression paramExpression = new ParamExpression(rule);

                    this.headerRuleMap.put(entry.getKey(), paramExpression);
                }
            }
        }
    }

    // below is the functions using in expression
    public static String md5(final String source) {
        return DigestUtils.md5Hex(source);
    }

    public static String date(final String format) {
        return DateUtil.dateToString(new Date(), format);
    }

    public static String decodeBase64(final String src) {
        return new String(Base64.decodeBase64(src));
    }

    public static String encodeBase64(final String src) {
        return Base64.encodeBase64URLSafeString(src.getBytes());
    }

    public static String urlEncode(final String src) {
        try {
            return URLEncoder.encode(src, "utf-8");
        } catch (final UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String sadd(final Object... objs) {
        if (null == objs) {
            return "null";
        }
        final StringBuffer stringBuffer = new StringBuffer();
        for (final Object o : objs) {
            if (null == o) {
                stringBuffer.append("null");
            } else {
                stringBuffer.append(o.toString());
            }
        }
        return stringBuffer.toString();
    }

    public static String strAdd(final Object... objs) {
        if (null == objs) {
            return "";
        }
        final StringBuffer stringBuffer = new StringBuffer();
        for (final Object o : objs) {
            if (null != o) {
                stringBuffer.append(o.toString());
            }
        }
        return stringBuffer.toString();
    }

    public static long nowTime() {
        return System.currentTimeMillis();
    }

    public static long nowUnixTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static void main(final String[] args) {

/*        org.apache.commons.jexl3.JexlEngine jexl = new JexlBuilder().create();
        JexlExpression ee = jexl.createExpression("currentTimeMillis");
        org.apache.commons.jexl3.JexlContext jc = new org.apache.commons.jexl3.MapContext();
        HashMap<Object, Object> ctxMap = new HashMap<>();
        ctxMap.put("phone", "123123");
        jc.set("$p", ctxMap);
        jc.set("user", "123456");
        jc.set("currentTimeMillis", nowTime());
        Object v = ee.evaluate( jc);
        System.out.println(v);
        final String s = sadd(null, "123", true, 111);
        String str = "{\"method\": \"get\",\"headerParam\":{\"Authorization\":\"$p._sys.pwd,':',$p._sys.user\"}}";
        JSONObject ob = JSONObject.parseObject(str);

        JexlEngine jexl = new JexlBuilder().create();
        JexlContext jc = new MapContext();

        JexlExpression ee = jexl.createExpression(ob.getJSONObject("headerParam").getString("Authorization"));
        HashMap<Object, Object> cx = new HashMap<>();
        cx.put("user", "hanhan");
        cx.put("pwd", "mima");
        HashMap<Object, Object> ctxMap = new HashMap<>();
        ctxMap.put("_sys", cx);

        jc.set("$p", ctxMap);
        jc.set("$", ParamRule.class);

        Object a = ee.evaluate(jc);
        System.out.println(a);*/

        Map<Object, Object> ctxMap = new HashMap<Object, Object>();
        ctxMap.put("phone","11111111");
        ctxMap.put("address","山东省");
        //ctxMap.put("curDate","curDatessss");

        JexlEngine jexl1 = new JexlBuilder().create();
        JexlContext jc1= new MapContext();
        jc1.set("$p",ctxMap);
        jc1.set("$",ParamRule.class);
        jc1.set("currentTimeMillis",System.currentTimeMillis());
        String a = "$.md5(`${$p._sys.pwd}!${$p._sys.user}!${currentTimeMillis}!${$p.phone}!${$p.address}!${$p.curDate}!${$p._sys.pwd}!`)";
        String script = "if(empty($p.curDate)){`{\\\"phone\\\":\\\"${$p.phone}\\\",\\\"address\\\":\\\"${$p.address}\\\"}`}else{`{\\\"phone\\\":\\\"${$p.phone}\\\",\\\"address\\\":\\\"${$p.address}\\\",\\\"curDate\\\":\\\"${$p.curDate}\\\"}`}";
        String scrip1t = "if(empty($p.curDate)){$.md5(`${$p._sys.pwd}!${$p._sys.user}!${currentTimeMillis}!${$p.phone}!${$p.address}!${$p._sys.pwd}!`)}else{$.md5(`${$p._sys.pwd}!${$p._sys.user}!${currentTimeMillis}!${$p.phone}!${$p.address}!${$p.curDate}!${$p._sys.pwd}!`)}";
        JexlScript jexlScript = jexl1.createScript(scrip1t);
        Object object = jexlScript.execute(jc1);
        System.out.println(object.toString());

    }

    public String evalJsonBody(HashMap<String, Object> ctxMap) {
        final HashMap<String, Object> data = new HashMap<String, Object>(3);
        data.put("p", ctxMap);
        data.put("timestampSec", nowUnixTime());
        data.put("currentTimeMillis", nowTime());

        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel staticMethods = null;
        try {
            staticMethods = (TemplateHashModel) staticModels.get(ParamRule.class.getCanonicalName());
        } catch (TemplateModelException e) {
            throw new RuntimeException(e);
        }
        data.put("$", staticMethods);

        final Writer out = new StringWriter();
        try {
            jsonBodyTempl.process(data, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }

    public String getContentType() {
        return contentType;
    }
}
