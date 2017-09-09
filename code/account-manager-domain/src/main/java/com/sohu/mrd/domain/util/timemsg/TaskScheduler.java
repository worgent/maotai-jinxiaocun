package com.sohu.mrd.domain.util.timemsg;

import com.sohu.mrd.domain.util.timemsg.beans.RebootSchedulerExceptionCheck;

import java.util.List;

/**
 * taskScheduler对象
 * @author chx
 * @date 2013-08-22 17:44:30
 */
public class TaskScheduler {

	/** 启动状态:1已启动,0未启动 */
	private int isStart = 0;
	
	private String schedulerName = "";
	
	private String quartzName = "";
	
	private List<TaskTrigger> triggers = null;

    private String machineIp="";

    private RebootSchedulerExceptionCheck rebootSchedulerExceptionCheck;

	public int getIsStart() {
		return isStart;
	}

	public void setIsStart(int isStart) {
		this.isStart = isStart;
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public String getQuartzName() {
		return quartzName;
	}

	public void setQuartzName(String quartzName) {
		this.quartzName = quartzName;
	}

	public List<TaskTrigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<TaskTrigger> triggers) {
		this.triggers = triggers;
	}

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public RebootSchedulerExceptionCheck getRebootSchedulerExceptionCheck() {
        return rebootSchedulerExceptionCheck;
    }

    public void setRebootSchedulerExceptionCheck(RebootSchedulerExceptionCheck rebootSchedulerExceptionCheck) {
        this.rebootSchedulerExceptionCheck = rebootSchedulerExceptionCheck;
    }
}
