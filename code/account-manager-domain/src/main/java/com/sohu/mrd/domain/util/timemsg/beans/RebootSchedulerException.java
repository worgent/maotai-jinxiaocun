package com.sohu.mrd.domain.util.timemsg.beans;

public class RebootSchedulerException {

	private long timestamp; // ��һ�η����쳣��ʱ��
	private String machineIp; // �����쳣�Ļ���
	private String machineName; // �����쳣���������ƣ���ʱû���õ�

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
