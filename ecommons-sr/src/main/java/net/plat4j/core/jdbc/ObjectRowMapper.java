package net.plat4j.core.jdbc;

import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.springframework.jdbc.core.RowMapper;

import org.net.plat4j.sr.core.utils.LogHelper;

@SuppressWarnings("rawtypes")
public class ObjectRowMapper implements RowMapper<Object> {
	protected final LogHelper logger = new LogHelper(getClass());
	Class bean;

	public ObjectRowMapper(Class a) {
		this.bean = a;
	}

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		try {
			Object obj = this.bean.newInstance();
			DynaBean wrapper = new WrapDynaBean(obj);

			ResultSetMetaData md = rs.getMetaData();
			Map<String, Object> validColumns = new HashMap<String, Object>();
			int columnCount = md.getColumnCount();
			for(int i = 1;i <= columnCount;i ++) {
				validColumns.put(md.getColumnLabel(i).toUpperCase(), md.getColumnTypeName(i));
			}

			Method[] methods = obj.getClass().getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];
				if ((m.getName().startsWith("get"))
						&& (m.getName().compareTo("getClass") != 0)) {
					String methodName = m.getName();
					String fieldName = methodName.substring(3, 4).toLowerCase()
							+ methodName.substring(4);
					String fieldType = m.getReturnType().getName();
					if(!validColumns.containsKey(fieldName.toUpperCase()))
						continue;
					try {
						wrapper.set(fieldName, buildObj(fieldType, rs, fieldName));
					} catch (Exception e) {
						this.logger.warn(fieldName + " field error:"
								+ e.getMessage());
					}
				}
			}
			return obj;
		} catch (IllegalAccessException ex) {
			logger.error(ex);
		} catch (InstantiationException ex) {
			logger.error(ex);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	private Object buildObj(String type, ResultSet rs, String fieldName)
			throws NumberFormatException, SQLException {
		Object obj = rs.getObject(fieldName);
		if(rs.wasNull()) {
			return null;
		}
		switch(type) {
			case "java.lang.String":
				if(obj instanceof Clob)
					//String clobString = obj.getSubString(1, (int) obj.length());//clob 转 String
			       // String blobString = new String(b.getBytes(1, (int) b.length()),"GBK");
					//return ((oracle.sql.CLOB)obj).stringValue();
					return ((Clob)obj).getSubString(1, (int) ((Clob)obj).length());
				else
					return String.valueOf(obj);
			case "java.lang.Long":
				return rs.getLong(fieldName);
			case "java.util.Date":
//				java.sql.Date ret = rs.getDate(fieldName);
				Timestamp ts = rs.getTimestamp(fieldName);
				return ts != null ? new Date(ts.getTime()) : null;
			case "java.lang.Boolean":
				return rs.getBoolean(fieldName);
			case "java.math.BigDecimal":
				return rs.getBigDecimal(fieldName);
			case "java.sql.Date":
				return rs.getDate(fieldName);
			case "java.sql.Timestamp":
				return rs.getTimestamp(fieldName);
			case "java.sql.Time":
				return rs.getTime(fieldName);
			case "java.lang.Integer":
				return rs.getInt(fieldName);
			case "java.lang.Byte":
				return rs.getByte(fieldName);
			case "java.lang.Float":
				return rs.getFloat(fieldName);
			case "java.lang.Double":
				return rs.getDouble(fieldName);
			default:
				return rs.getObject(fieldName);
		}
	}
}
