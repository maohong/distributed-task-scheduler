package org.mh.dts.common.http.servlet;

import java.io.Serializable;

public abstract class SchedulerServerResponse implements Serializable {

	abstract public String toJson();
}
