package org.mh.dts.scheduler.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by maohong on 2016/12/25.
 */


public class Main {

    private Server server;

    public void start() throws Exception{
        server = new Server(8080);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
//        webAppContext.setDescriptor("/Users/maohong/code/myOpenSource/distributed-task-scheduler/dts-scheduler/src/main/webapp/WEB-INF/web.xml");
        webAppContext.setResourceBase("/Users/maohong/code/myOpenSource/distributed-task-scheduler/dts-scheduler/src/main/webapp");
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);

        // 以下代码是关键
//        webAppContext.setClassLoader(applicationContext.getClassLoader());
//        webAppContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
//                applicationContext);

        server.start();
        server.join();
    }

    public static void main(String [] args) throws Exception {

        Main jettyDaemon = new Main();
        jettyDaemon.start();

    }

}
