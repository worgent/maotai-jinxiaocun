package com.sohu.mrd.domain.util.timemsg;

public class TaskTrigger {

	private String triggerName = "";
	
	private String triggerCronExpression = "";
	
	private String quartzName = "";

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerCronExpression() {
		return triggerCronExpression;
	}

	public void setTriggerCronExpression(String triggerCronExpression) {
		this.triggerCronExpression = triggerCronExpression;
	}

	public String getQuartzName() {
		return quartzName;
	}

	public void setQuartzName(String quartzName) {
		this.quartzName = quartzName;
	}
	
}
