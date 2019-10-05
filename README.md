## 将jar包发布到中央仓库
`mvn clean deploy '-Dmaven.test.skip=true'`

## 源地址
> https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console

## 钉钉报警
参考：https://blog.csdn.net/luanlouis/article/details/88078657

## 扩展实现的代码
```$xslt
@Scheduled(cron = "0 */1 * * * ?")
public void scanProblemConsumeGroup() {
    System.out.println("每1分钟监控一次");
    for (Map.Entry<String, ConsumerMonitorConfig> configEntry : monitorService.queryConsumerMonitorConfig().entrySet()) {
        GroupConsumeInfo consumeInfo = consumerService.queryGroup(configEntry.getKey());
        if (consumeInfo.getCount() < configEntry.getValue().getMinCount() || consumeInfo.getDiffTotal() > configEntry.getValue().getMaxDiffTotal()) {
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
```

## 使用方式
#### 下载打包

`cd project`
`mvn clean package`

#### 运行jar包

```$xslt
cd target
java -jar rocketmq-console-ng-1.0.0.jar --server.port=6789 --rocketmq.config.namesrvAddr=192.168.1.10:9876 --dingding.token=***12a51a37466df312f420d4157a
0648effe59bcd725522300edae853401f4f --dingding.phones=***21939122
```
1. server.port:指定端口号
1. rocketmq.config.namesrvAddr:指定rocketmq地址，多个使用分号`;`分隔
1. dingding.token:钉钉报警机器人token值
1. dingding.phones:@人的手机号，所有的话使用all，多个人的话使用逗号`,`分隔


![aaa](https://github.com/chywx/rocketmq-console-alarm/blob/master/src/main/resources/chy/2.png?raw=true)

minCount 最少需要几个提供者
maxDiffTotal 消息堆积量
#### 效果
![bbb](https://github.com/chywx/rocketmq-console-alarm/blob/master/src/main/resources/chy/1.png?raw=true)