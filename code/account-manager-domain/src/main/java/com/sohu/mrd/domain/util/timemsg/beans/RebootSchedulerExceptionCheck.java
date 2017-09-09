package com.sohu.mrd.domain.util.timemsg.beans;

public class RebootSchedulerExceptionCheck {

	private int status;
	private long timestamp; // 第一次发生异常的时间
	private long count = 0; // 发生异常的次数
	private String machineIp; // 发生异常的机器
	private String machineName; // 发生异常机器的名称，暂时没有用到
	private long rebootTimestamp; // 重启机器的时间
	private int checkFlag = 0; // 机器是否重启中的标志位，1，机器重启中，0，已重启完毕(该重启表示从重启开始，等待5分钟以上，则任务已经重启完毕)

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
