package org.net.plat4j.sr.core.base;

import java.util.ArrayList;
import java.util.List;
/**
 * 提供查询的sqlBuffer查询
 * 
 * @author chenshiming
 * @usage:
		BaseSqlBuffer sqlBuffer = new BaseSqlBuffer();
		sqlBuffer.addCauseIfNotNull("and t1.create_Time > to_date(?,'yyyy-mm-dd')",spModel.getBeginCreateTime());
		sql.append(sqlBuffer.getBuffer());	
		PageTools pageTools = getPage(spModel);
		List list = this.queryForPageBySql(sql.toString(),sqlBuffer.getData(),pageTools,QueryCompanyModel.class);
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseSqlBuffer {
	private StringBuffer buffer = new StringBuffer();
	private List data = new ArrayList();
	
	private void addCauseIfNotNull(String sql,String obj,String sqlValue){
		if (obj != null && obj.trim().length() > 0) {
			buffer.append(" "+sql);
			String ss = sql;		
			//計算出現的次數
			int count = (ss.length()-ss.replaceAll("\\?", "").length())/("\\?".length()-1);
			for(int i=0;i<count;i++){
				data.add(sqlValue);
			}	
		}	
	}
	
	public void addCauseIfNotNull(String sql,String obj){
		addCauseIfNotNull(sql,obj,obj);
	}
 	public void addLikeCauseIfNotNull(String sql,String obj){
 		addCauseIfNotNull(sql,obj,"%"+obj+"%");	
	}	
	public void addLeftLikeCauseIfNotNull(String sql,String obj){
		addCauseIfNotNull(sql,obj,"%"+obj);	
	}
	public void addRightLikeCauseIfNotNull(String sql,String obj){
		addCauseIfNotNull(sql,obj,obj+"%");		
	}	 
	
	public StringBuffer getBuffer(){
		return buffer;
	}
	
	public void add(Object sqlValue) {
		data.add(sqlValue);
	}
	public String[] getData(){
		String[] rr = new String[data.size()];
		data.toArray(rr);
		return rr;
	}
	
//	
//	private static String getCauseIfNotNull(String cause, String obj) {
//		cause = " " + cause.replaceAll("'", "''") + " ";
//		if (obj != null && obj.trim().length() > 0) {
//			return MessageFormat.format(cause, new Object[] { obj });
//		}
//		return "";
//	}	
}
