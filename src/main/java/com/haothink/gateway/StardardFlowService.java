package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao
 *
 * 标准流程控制层
 **/
public class StardardFlowService {


    private RequestDataConverter requestDataConverter;

    private ResponseDataConverter responseDataConverter;

    private OrgApiClientRouter orgApiClientRouter;

    public void openEleAccount(EleAccountCreateReq eleAccountCreateReq) {

        String param = requestDataConverter.convert(eleAccountCreateReq);
        String responseData = orgApiClientRouter.invoke(param);
        EleAccountInfo eleAccountInfo = responseDataConverter.convert(responseData);
        System.out.println(eleAccountInfo);

    }


}
