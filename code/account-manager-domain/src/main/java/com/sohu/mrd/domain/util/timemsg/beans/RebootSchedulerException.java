package com.sohu.mrd.domain.util.timemsg.beans;

public class RebootSchedulerException {

	private long timestamp; // 第一次发生异常的时间
	private String machineIp; // 发生异常的机器
	private String machineName; // 发生异常机器的名称，暂时没有用到

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

}
