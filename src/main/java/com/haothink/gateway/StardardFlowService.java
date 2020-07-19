package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao@didiglobal.com
 *
 * 标准流程控制层
 **/
public class StardardFlowService {


    private RequestDataConverter requestDataConverter;

    private ResponseDataConverter responseDataConverter;

    private OrgApiRouter orgApiRouter;

    public void openEleAccount(EleAccountCreateReq eleAccountCreateReq) {

        String param = requestDataConverter.convert(eleAccountCreateReq);
        String responseData = orgApiRouter.invoke(param);
        EleAccountInfo eleAccountInfo = responseDataConverter.convert(responseData);
        System.out.println(eleAccountInfo);

    }


}
