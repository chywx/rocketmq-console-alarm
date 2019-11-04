package cn.chendahai.rocketmq.console.task;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemMsg {

    public static String getHostAddress() {
        String address = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            address = addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address;
    }

}
