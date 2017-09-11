/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月4日 下午4:11:32
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月4日下午4:11:32		WGY			Create file
=========================================================================
*/

package org.net.plat4j.common.quartz;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * quartz定时器工具类
 * @author WGY 2017年3月4日
 *
 */
public class EbiddingScheduler {
	private static LogHelper logger = new LogHelper(EbiddingScheduler.class);
	private volatile  static Scheduler scheduler;
	
	private static Lock lock = new ReentrantLock();
	
	private  static void init() {
		 SchedulerFactory sf = new StdSchedulerFactory();  
         try { 
        	 scheduler = sf.getScheduler(); 
        	 scheduler.start();
         } catch (SchedulerException e) { 
        	 logger.error(e);
         }
	}
	
	/**
	 * 获取调度器
	 * @return
	 */
	public  static Scheduler getScheduler() {
		 if(scheduler == null) {
			 lock.lock();
			 try {
				 if(scheduler == null) {
					 init();
				 }
			 } finally {
				 lock.unlock();
			 }
		 }
		 return scheduler;
	}
	
	/**
	 * 获取定时器剩余时间
	 * @param trigger
	 * @return
	 */
	public static long getRemainingTime(Trigger trigger) {
		
		Date currentTime = new Date();
		Date startTime = trigger.getStartTime();
		long remainingTime = startTime.getTime() - currentTime.getTime();
		return remainingTime;
	}
}

