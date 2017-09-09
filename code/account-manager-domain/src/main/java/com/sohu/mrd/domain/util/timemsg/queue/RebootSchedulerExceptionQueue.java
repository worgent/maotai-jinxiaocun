package com.sohu.mrd.domain.util.timemsg.queue;


import com.sohu.mrd.domain.util.timemsg.beans.RebootSchedulerException;
import com.sohu.mrd.domain.util.timemsg.beans.RebootSchedulerExceptionCheck;

import java.util.LinkedList;
import java.util.Queue;

public class RebootSchedulerExceptionQueue {

	/*
	 * 检测发生异常的机器是否恢复正常
	 */
	// public static BlockingQueue<RebootSchedulerException>
	// rebootExceptionQueue = new
	// LinkedBlockingQueue<RebootSchedulerException>();
	public static Queue<RebootSchedulerException> rebootExceptionQueue = new LinkedList<RebootSchedulerException>();

	public static Queue<RebootSchedulerExceptionCheck> rebootExceptionCheckQueue = new LinkedList<RebootSchedulerExceptionCheck>();

}
