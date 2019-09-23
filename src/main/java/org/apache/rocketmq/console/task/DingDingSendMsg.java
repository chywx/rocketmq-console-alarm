package org.apache.rocketmq.console.task;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;

import java.util.Arrays;

/**
 * 功能描述
 *
 * @author chy
 * @date 2019/9/18 0018
 */
public class DingDingSendMsg {

//    云睿钉钉群
    public static final String TOKEN = "***12a51a37466df312f420d4157a0648effe59bcd725522300edae853401f4f";

    public static DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token="+TOKEN);

    public static void send(String msg) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(msg);
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("13121939122"));
        request.setAt(at);
        try {
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
