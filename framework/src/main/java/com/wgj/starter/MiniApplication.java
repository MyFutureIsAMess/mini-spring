package com.wgj.starter;

import com.wgj.web.server.TomcatServer;
import org.apache.catalina.LifecycleException;

/**
 * @author Guojian Wang
 * @version 1.0
 * @date 2019/7/27 - 0:46
 * @since 1.0
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　 ┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class MiniApplication {
    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
