/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.chendahai.rocketmq.console.task;

import java.util.Map;
import javax.annotation.Resource;
import cn.chendahai.rocketmq.console.model.ConsumerMonitorConfig;
import cn.chendahai.rocketmq.console.model.GroupConsumeInfo;
import cn.chendahai.rocketmq.console.service.ConsumerService;
import cn.chendahai.rocketmq.console.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitorTask {
    private Logger logger = LoggerFactory.getLogger(MonitorTask.class);

    @Resource
    private MonitorService monitorService;

    @Resource
    private ConsumerService consumerService;

    @Autowired
    DingDingSendMsg dingDingSendMsg;

    @Scheduled(cron = "0 */1 * * * ?")
    public void scanProblemConsumeGroup() {
        System.out.println("chy每1分钟监控一次");
        for (Map.Entry<String, ConsumerMonitorConfig> configEntry : monitorService.queryConsumerMonitorConfig().entrySet()) {
            GroupConsumeInfo consumeInfo = consumerService.queryGroup(configEntry.getKey());
            if (consumeInfo.getCount() < configEntry.getValue().getMinCount() || consumeInfo.getDiffTotal() > configEntry.getValue().getMaxDiffTotal()) {
//                logger.info("chy=look consumeInfo {}", JsonUtil.obj2String(consumeInfo)); // notify the alert system
                String msg = "消息堆积详情"+
                        "\ngroup→"+consumeInfo.getGroup()+
                        "\ncount→"+consumeInfo.getCount()+
                        "\nmessageModel→"+consumeInfo.getMessageModel()+
                        "\nconsumeTps→"+consumeInfo.getConsumeTps()+
                        "\ndiffTotal→"+consumeInfo.getDiffTotal();
                dingDingSendMsg.send(msg);
            }
        }
    }
}
