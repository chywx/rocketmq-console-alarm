package cn.chendahai.rocketmq.console.task;

import java.util.List;

/**
 * 功能描述
 *
 * @author chy
 */
public class AtMobiles {

    /**
     * 被@人的手机号
     *
     * @return
     */
    private List<String> atMobiles;

    /**
     * @所有人时:true,否则为:false
     */
    private Boolean isIsAtAll;

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public Boolean getIsAtAll() {
        return isIsAtAll;
    }

    public void setIsAtAll(Boolean isAtAll) {
        isIsAtAll = isAtAll;
    }
}
