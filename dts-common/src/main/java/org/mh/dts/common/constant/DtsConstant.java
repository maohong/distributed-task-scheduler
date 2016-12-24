package org.mh.dts.common.constant;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by maohong on 2016/12/18.
 */
@Slf4j
public class DtsConstant {


    public static String ipAddress = null;

    static {

        try {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }

    }
}
