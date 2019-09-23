# 源地址
> https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console

# 扩展实现

#### 钉钉报警
参考：https://blog.csdn.net/luanlouis/article/details/88078657

使用方式：
打包后执行命令
```$xslt
java -jar rocketmq-console-ng-1.0.0.jar --server.port=6789 --rocketmq.config.namesrvAddr=192.168.1.10:9876 --dingding.token=***12a51a37466df312f420d4157a
0648effe59bcd725522300edae853401f4f --dingding.phones=***21939122
```
1. server.port:指定端口号
1. rocketmq.config.namesrvAddr:指定rocketmq地址，多个使用分号`;`分隔
1. dingding.token:钉钉报警机器人token值
1. dingding.phones:@人的手机号，所有的话使用all，多个人的话使用逗号`,`分隔



![aaa](https://img-blog.csdnimg.cn/20190505092407771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9sb3VsdWFuLmJsb2cuY3Nkbi5uZXQ=,size_16,color_FFFFFF,t_70)