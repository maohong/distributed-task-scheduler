package org.mh.dts.scheduler.web;

import org.mh.dts.common.http.servlet.BaseServlet;
import org.mh.dts.common.http.servlet.SchedulerServerResponse;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by maohong on 2016/12/17.
 */
public class TaskScheduleServlet extends BaseServlet {


    private static final long serialVersionUID = 6466459142819371789L;

    @Override
    protected SchedulerServerResponse processRequest(Map<String, Object> param, HttpSession session) {
        return null;
    }

}
