package org.net.plat4j.sr.core.utils.impl;

import java.util.Random;

import org.net.plat4j.sr.core.utils.IBaseRandamUtils;

public class BaseRandamUtils implements IBaseRandamUtils {
	private static int seed = 0;
	public String getUniqueRandamString() {
		if(seed>1000){
			seed = 0;
		}
		return System.currentTimeMillis()+""+new Random(seed++).nextInt(100);
	}

}
