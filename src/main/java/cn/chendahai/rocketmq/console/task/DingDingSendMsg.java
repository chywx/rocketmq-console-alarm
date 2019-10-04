package cn.chendahai.rocketmq.console.task;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
 * 功能描述
 *
 * @author chy
 * @date 2019/9/18 0018
 */
@Component
public class DingDingSendMsg {

    @Autowired
    private Environment environment;

    public void send(String msg) {
        String TOKEN = environment.getProperty("dingding.token");
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token="+TOKEN);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(msg);
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList(environment.getProperty("dingding.phones")));
        request.setAt(at);
        try {
            client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void aaa(){
        String property = environment.getProperty("chy.aaa");
        System.out.println("chy:"+property);
    }
}
