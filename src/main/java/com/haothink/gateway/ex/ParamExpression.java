package com.haothink.gateway.ex;

import org.apache.commons.jexl3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanghao on 2020-07-19
 * mail:hiwanghao@didiglobal.com
 **/
public class ParamExpression {

    private static final JexlEngine jexlEngine = new JexlBuilder().create();

    private static final Logger logger = LoggerFactory.getLogger(ParamExpression.class);

    public static final String SCRIPT_PREFIX = "¥";

    private org.apache.commons.jexl3.JexlExpression JexlExpression;

    private JexlScript jexlScript;

    private Boolean isScript = false;



    public ParamExpression(String rule) {
        if (StringUtils.isNotBlank(rule) && rule.startsWith(SCRIPT_PREFIX)) {
            this.isScript = true;
            rule = StringUtils.removeFirst(rule, SCRIPT_PREFIX);
            this.jexlScript = jexlEngine.createScript(rule);
        } else {
            this.JexlExpression = jexlEngine.createExpression(rule);
        }
    }


    public String eval(JexlContext jc) {
        Object v = null;
        try {
            if (this.isScript) {
                v = this.jexlScript.execute(jc);

            } else {

                v = this.JexlExpression.evaluate(jc);

            }
        } catch (final JexlException e) {
            if (logger.isDebugEnabled()) {
                logger.error("evaluate expression error", e);
            }
        }
        return v == null ? "" : v.toString();
    }

}
