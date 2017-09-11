package org.net.plat4j.sr.core.base;

import org.net.plat4j.sr.core.utils.LogHelper;

public class BaseTimeMonitor {
	protected LogHelper logger = new LogHelper(getClass());
	private String key;
	private double begin;
	private double end;
	
	public BaseTimeMonitor(String key){
		this.key = key;
	}
	
	public void beginMonitor(){
		logger.debug("Begin monitor:"+key);
		this.begin = System.currentTimeMillis();
	}
	public void print(){
		this.end = System.currentTimeMillis();
		logger.debug(key+" process "+(end - begin) +" ms");
		//System.out.println(key+" process "+(end - begin) +" ms");
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	
	

}
