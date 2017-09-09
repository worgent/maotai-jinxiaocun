package com.sohu.mrd.domain.util.timemsg.beans;

public class RebootSchedulerExceptionCheck {

	private int status;
	private long timestamp; // ��һ�η����쳣��ʱ��
	private long count = 0; // �����쳣�Ĵ���
	private String machineIp; // �����쳣�Ļ���
	private String machineName; // �����쳣���������ƣ���ʱû���õ�
	private long rebootTimestamp; // ����������ʱ��
	private int checkFlag = 0; // �����Ƿ������еı�־λ��1�����������У�0�����������(��������ʾ��������ʼ���ȴ�5�������ϣ��������Ѿ��������)

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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getRebootTimestamp() {
		return rebootTimestamp;
	}

	public void setRebootTimestamp(long rebootTimestamp) {
		this.rebootTimestamp = rebootTimestamp;
	}

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RebootSchedulerExceptionCheck [status=" + status
				+ ", timestamp=" + timestamp + ", count=" + count
				+ ", machineIp=" + machineIp + ", machineName=" + machineName
				+ ", rebootTimestamp=" + rebootTimestamp + ", checkFlag="
				+ checkFlag + "]";
	}

}
