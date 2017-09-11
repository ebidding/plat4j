package org.net.plat4j.common.utils;

public class ConfigUtilsTest {
	
	public static void main(String[] args) {
//		String jstr0 = "[{\"id\": \"2\"}]";
		String jstr1 = "{\"item\":[{\"layOut\":{\"portletLength\":\"2\",\"width\":\"1\"},\"protlets\":[{\"id\":\"1\",\"name\":\"待办提醒\",\"type\":\"left\",\"portletClass\":\"undefined\",\"uri\":\"AgentmanageAction.do?method=queryAgentManage\",\"portletMust\":\"1\"}],\"width\":\"\"},{\"layOut\":{\"portletLength\":\"4\",\"width\":\"2\"},\"protlets\":[{\"id\":\"2\",\"name\":\"通知公告\",\"type\":\"left\",\"portletClass\":\"undefined\",\"uri\":\"AnnouncementAction.do?method=queryAnnouncement\",\"portletMust\":\"1\"}],\"width\":\"\"}]}";
		String jstr2 = "{\"item\":[{\"layOut\":{\"portletLength\":\"2\",\"width\":\"1\"},\"protlets\":[{\"id\":\"5\",\"name\":\"项目进度一览表\",\"type\":\"middle\",\"portletClass\":\"undefined\",\"uri\":\"ScheduleAction.do?method=querySchedule\",\"portletMust\":\"0\"}],\"width\":\"\"},{\"layOut\":{\"portletLength\":\"4\",\"width\":\"2\"},\"protlets\":[{\"id\":\"6\",\"name\":\"出差备案\",\"type\":\"right\",\"portletClass\":\"undefined\",\"uri\":\"TravelRecordAction.do?method=queryTravelRecord\",\"portletMust\":\"0\"}],\"width\":\"\"}]}";
		
		String ret = ConfigUtils.mergeJsonConfig(new String[] {jstr1, jstr2}, "item,[,protlets,[,id");
		System.out.println(ret);
	}
}
