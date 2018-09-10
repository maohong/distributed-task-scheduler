package org.mh.dts.scheduler.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author maohong@sogou-inc.com
 * 2017/5/27 17:59
 */
public class JettyWebServer implements InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(JettyWebServer.class);

    private static final int DEFAULT_HTTP_PORT = 9090;
    private static final String DEFAULT_CONTEXT_PATH = "/";
    private static final String DEFAULT_WEBAPP_PATH = "dts-scheduler/src/main/webapp";
    private static final String DEFAULT_WEBXML_PATH = DEFAULT_WEBAPP_PATH + "/WEB-INF/web.xml";

    private Server server;

    @Override
    public void afterPropertiesSet() throws Exception {

        startJetty();

    }


    @Override
    public void destroy() throws Exception {

        stopJetty();
    }

    /**
     * 启动jetty服务
     */
    public void startJetty() {

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        try {
            server = createJettyWebServer(DEFAULT_HTTP_PORT, DEFAULT_CONTEXT_PATH);
            server.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

    /**
     * 停止jetty服务
     */
    public void stopJetty() {

        try {
            server.stop();
            server.join();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

    public static Server createJettyWebServer(int port, String contextPath) {

        // 设置jetty内置的日志级别为INFO
        final Logger logger = LoggerFactory.getLogger("org.eclipse.jetty");
        if (logger instanceof ch.qos.logback.classic.Logger) {
            ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger) logger;
            logbackLogger.setLevel(ch.qos.logback.classic.Level.INFO);
        }

        Server server = new Server(port);
        server.setStopAtShutdown(true);
        WebAppContext webContext;
//        if (SystemConstant.isProduct) { //product
//            ProtectionDomain protectionDomain = JettyWebServer.class.getProtectionDomain();
//            URL location = protectionDomain.getCodeSource().getLocation();
//            String warFilePath = location.toExternalForm();
//            logger.info("war file path:" + warFilePath);
//            webContext = new WebAppContext(warFilePath, contextPath);
//            // 设置work dir,war包将解压到该目录，jsp编译后的文件也将放入其中。
//            String currentDir = new File(location.getPath()).getParent();
//            File workDir = new File(currentDir, "work");
//            webContext.setTempDirectory(workDir);
//        }
//        else
        { //dev
            //这是http的连接器
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port);
            connector.setReuseAddress(false);
            server.setConnectors(new Connector[]{connector});
            webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
            webContext.setDescriptor(DEFAULT_WEBXML_PATH);
            webContext.setResourceBase(DEFAULT_WEBAPP_PATH); // 设置webapp的位置，若报404，检查src/main/webapp绝对路径是否正确
            webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        }
        webContext.setServer(server);
        server.setHandler(webContext);
        return server;
    }

}
