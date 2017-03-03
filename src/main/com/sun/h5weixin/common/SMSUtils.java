package main.com.sun.h5weixin.common;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;

/**
 * Created by SUN on 2017/3/2.
 */
public class SMSUtils {
    public String sendCheckCodeSMS(String code, String mobile)
    {
        String sms_url = "http://gw.api.taobao.com/router/rest";
        String sms_app_key = "23436177";
        String sms_app_secret = "616ea5dc20209e718e91586b92c0c8bd";
        TaobaoClient client = new DefaultTaobaoClient(sms_url, sms_app_key, sms_app_secret);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend(mobile);
        request.setSmsType("normal");
        request.setSmsFreeSignName("闪蝠众包");
        request.setSmsParamString("{\"code\":\"" + code + "\"}");
        request.setRecNum(mobile);
        request.setSmsTemplateCode("SMS_15520547");
        try
        {
            client.execute(request);
            return "";
        }catch (ApiException e)
        {
            e.printStackTrace();
            return e.getErrMsg();
        }
    }
}
