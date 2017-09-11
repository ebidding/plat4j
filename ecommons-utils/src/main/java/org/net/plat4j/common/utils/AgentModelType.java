package org.net.plat4j.common.utils;

import java.util.Map;



import org.net.plat4j.common.agentModelType.IAgentModelType;

import net.plat4j.core.spring.BeanFactory;

public class AgentModelType {
	public static Map<String,String>getModelType(String model){
		IAgentModelType service=(IAgentModelType)BeanFactory.getBean("IAgentModelType");
		return service.getModelUnit(model);
	}
}
