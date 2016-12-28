package org.mh.dts.scheduler.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * Created by maohong on 2016/12/25.
 */


public class Main {

    private Server server;

    public void start() throws Exception{
        server = new Server(8080);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        System.out.println(Main.class.getResource("/"));
        System.out.println(Main.class.getResource("/").getPath());
        File baseResourceDir = new File(Main.class.getResource("/").getPath());
        System.out.println(baseResourceDir.getAbsolutePath());
        webAppContext.setResourceBase(baseResourceDir.getParentFile().getParentFile().getAbsolutePath());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }

    public static void main(String [] args) throws Exception {

        Main jettyDaemon = new Main();
        jettyDaemon.start();

    }

}
