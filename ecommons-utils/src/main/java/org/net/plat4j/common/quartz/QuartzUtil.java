/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月4日 下午4:11:07
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月4日下午4:11:07		WGY			Create file
=========================================================================
*/

package org.net.plat4j.common.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import org.net.plat4j.sr.core.utils.LogHelper;

/**
 * quartz定时器使用工具类
 * @author WGY 2017年3月4日
 *
 */
public class QuartzUtil {
	private  LogHelper logger = new LogHelper(QuartzUtil.class);
	
	private String jobName;
	private String jobGroup;
	private String triggerName;
	private String triggerGroup;
	private Class<? extends Job> jobClazz;
	private Map<String, ? extends Object> params;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public Class<? extends Job> getJobClazz() {
		return jobClazz;
	}

	public void setJobClazz(Class<? extends Job> jobClazz) {
		this.jobClazz = jobClazz;
	}

	public Map<? extends Object, ? extends Object> getParams() {
		return params;
	}

	public void setParams(Map<String, ? extends Object> params) {
		this.params = params;
	}
	
	public QuartzUtil(){
		
	}
	
	public QuartzUtil( String jobName,String jobGroup,String triggerName,
			String triggerGroup,Class<? extends Job> jobClazz,Map<String,? extends Object> params) {
		
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.triggerName = triggerName;
		this.triggerGroup = triggerGroup;
		this.jobClazz = jobClazz;
		this.params = params;
	}
	
	public QuartzUtil( String name,String param ,
			Class<? extends Job> jobClazz,Map<String,? extends Object> params) {
		String jobName = name + "Job";
		String triggerName = name + "Trigger";
		
		this.jobName = jobName + param;
		this.jobGroup = jobName;
		this.triggerName = triggerName + param;
		this.triggerGroup = triggerName;
		this.jobClazz = jobClazz;
		this.params = params;
	}
	
	/**
	 * 启动一个定时器
	 * @param time 定时时间,定时任务在此时间触发
	 * @throws Exception
	 */
	public  void start(Date time) throws Exception {
		try {
			Scheduler scheduler = EbiddingScheduler.getScheduler();
			Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
		    if(null == trigger){
		    	logger.info("定时器启动，jobName="+jobName+"，定时器执行时间："+time);
				JobDetail jobDetail = newJob(jobClazz)
				            .withIdentity(JobKey.jobKey(jobName, jobGroup))
				            .build();
				if(null != params && !params.isEmpty()){
					jobDetail.getJobDataMap().putAll(params);
				}
		        trigger = newTrigger()
				            .withIdentity(TriggerKey.triggerKey(triggerName, triggerGroup))
				            .startAt(time)
				            .build();
				scheduler.scheduleJob(jobDetail, trigger);
		     }else{
		    	 if(!trigger.getStartTime().equals(time)) {
					reStart(time);
				}
		     }
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	
	/**
	 * 重新启动一个定时器
	 * @param time 定时时间,定时任务在此时间触发
	 * @throws Exception
	 */
	public void  reStart(Date time) throws Exception {
		logger.info("定时器重新启动，triggerName="+this.triggerName+"，定时器执行时间："+time);
		Scheduler scheduler =  EbiddingScheduler.getScheduler();
	    scheduler.pauseTrigger(TriggerKey.triggerKey(this.triggerName, this.triggerGroup));
	    scheduler.unscheduleJob(TriggerKey.triggerKey(this.triggerName, this.triggerGroup));
		start(time);
	}
	
	/**
	 * 停止一个定时器
	 * @throws Exception
	 */
	public void stop() throws Exception { 
		stop(false); 
	}
	
	public void stop(boolean waitForJobsToComplete) throws Exception {
					
		logger.info("定时器停止，jobName="+this.jobName);
		try {
			Scheduler scheduler = EbiddingScheduler.getScheduler();
			scheduler.pauseTrigger(TriggerKey.triggerKey(this.triggerName, this.triggerGroup));
			if(waitForJobsToComplete){
				scheduler.triggerJob(JobKey.jobKey(this.jobName, this.jobGroup));
			} 
			scheduler.unscheduleJob(TriggerKey.triggerKey(this.triggerName, this.triggerGroup)); 
		} catch(Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 获取定时器剩余定时时间，返回毫秒值，定时结束或没有此定时器在使用返回null
	 * @return 
	 * @throws Exception
	 */
	public Long getRemainingTime() throws Exception {
		Scheduler scheduler = EbiddingScheduler.getScheduler();
		Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(this.triggerName, this.triggerGroup));
		if(trigger == null){ 
			return null;
		}
		return EbiddingScheduler.getRemainingTime(trigger);
	}
}
