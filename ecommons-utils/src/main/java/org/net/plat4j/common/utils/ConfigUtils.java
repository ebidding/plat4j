/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-07 21:47:09
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-07 21:47:09		yujie				Create file
=========================================================================
 */

package org.net.plat4j.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.net.plat4j.api.utils.UtilsServiceFactory;
import org.net.plat4j.common.config.IConfigService;

/**
 * 配置管理访问接口工具类。
 * 
 * @author yujie
 */
public class ConfigUtils {
	/**
	 * 平台级配置。
	 */
	public static final int CONFIG_SCOPE_PLATFORM = 10;
	/**
	 * 公司类型级配置。
	 */
	public static final int CONFIG_SCOPE_COMPANY_TYPE = 20;
	/**
	 * 代理机构级别配置
	 */
	public static final int CONFIG_SCOPE_AGENT=21;
	/**
	 * 公司级配置。
	 */
	public static final int CONFIG_SCOPE_COMPANY = 30;
	/**
	 * 部门级配置。
	 */
	public static final int CONFIG_SCOPE_DEPARTMENT = 40;
	/**
	 * 角色级配置。
	 */
	public static final int CONFIG_SCOPE_ROLE = 41;
	/**
	 * 用户级配置。
	 */
	public static final int CONFIG_SCOPE_USER = 50;

	/**
	 * 保存指定级别的配置值到数据库中，并且刷新对应的缓存。 如果指定的配置项值不存在，则将创建新的记录，否则将更新已有的值。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:30:23
	 * </pre>
	 * 
	 * @param scope 配置项级别，可以使用 ConfigService 中的常量。
	 * @param scopeId 配置项级别对应对象的编号，对于平台级始终为 0，对于公司类型级为参见公司类型定义， 对于公司级为
	 *            SYS_COMPANY_ID，对于部门级为 SYS_COMPANY_DEPART_ID，对于角色级为 SYS_ROLE_ID，对于用户级为 SYS_USER_ID。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @param value 需要保存的配置项值。
	 * @return 保存是否成功。
	 */
	public static boolean saveConfigValue(int scope, long scopeId, String category, String key,
			String value) {
		return getConfigService().saveConfigValue(scope, scopeId, category, key, value);
	}

	/**
	 * 删除用户的配置 <br/>
	 * <br/>
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param scopeId 用户Id
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return
	 */
	public static void removeUserConfig(long scopeId, String category, String key) {
		getConfigService().removeUserConfig(scopeId, category, key);
	}

	/**
	 * 获取当前用户对应的指定分类和键值的配置项的值。获取时按照用户级、角色和部门级、公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。其中角色和部门级如果有多个有效配置，
	 * 将返回找到的第一个，具体返回值可能无法预测，所以对于排他型配置建议不要出现同级别的重复配置项，对于非排他型配置，可以使用
	 * <code>getUserConfigValues</code> 获取可能的所有配置值。<br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static String getUserConfigValue(String category, String key) {
		return getConfigService().getUserConfigValue(category, key);
	}
	
	/**
	 * 获取当前用户对应的指定分类和键值的配置项的所有可用配置值。获取时按照用户级、角色和部门级、公司级、公司类型级、平台级的顺序，返回
	 * 匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 空数组。 <br/>
	 * <br/>
	 * 如果指定的配置项被设置为排他的，返回值将只有一个值或者空数组，等同于<code>getUserConfigValues</code> 接口；如果指定的配置项是非排他的，
	 * 但是是级别间排他的，将返回能够找到的第一个级别上的所有配置项；如果指定的配置项不是排他并且也不是级别间排他的，将返回所有级别上的配置值。<br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-11-13 11:54:04
	 * </pre>
	 * 
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 如果指定的配置项被设置为排他的，返回值将只有一个值或者空数组，等同于<code>getUserConfigValues</code> 接口；如果指定的配置项是非排他的，
	 * 但是是级别间排他的，将返回能够找到的第一个级别上的所有配置项；如果指定的配置项不是排他并且也不是级别间排他的，将返回所有级别上的配置值。
	 */
	public static String[] getUserConfigValues(String category, String key) {
		return getConfigService().getUserConfigValues(category, key);
	}
	
	/**
	 * 获取当前用户对应的指定分类和键值的配置项的所有可用配置值。获取时按照用户级、角色和部门级、公司级、公司类型级、平台级的顺序，返回
	 * 匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 空数组。 <br/>
	 * <br/>
	 * 如果指定的配置项被设置为排他的，返回值将只有一个值或者空数组，等同于<code>getUserConfigValues</code> 接口；如果指定的配置项是非排他的，
	 * 但是是级别间排他的，将返回能够找到的第一个级别上的所有配置项；如果指定的配置项不是排他并且也不是级别间排他的，将返回所有级别上的配置值。<br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-11-13 11:54:04
	 * </pre>
	 * 
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @param idPath 形如 item,[,protlets,[,id
	 * @return JSON 格式的配置项合并字符串。
	 */
	public static String getUserConfigValuesJsonStr(String category, String key, String idPath) {
		return mergeJsonConfig(getConfigService().getUserConfigValues(category, key), idPath);
	}

	/**
	 * 获取当前用户所在公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param companyId 公司编号。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static String getCurCompanyConfigValue(String category, String key) {
		return getConfigService().getCompanyConfigValue(WebUtils.getCompanyId(), category, key);
	}

	/**
	 * 获取当前用户所在公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param companyId 公司编号。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static boolean getCurCompanyConfigBooleanValue(String category, String key) {
		return isTrue(getConfigService().getCompanyConfigValue(WebUtils.getCompanyId(), category,
				key));
	}

	/**
	 * 获取指定公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param companyId 公司编号。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static String getCompanyConfigValue(long companyId, String category, String key) {
		return getConfigService().getCompanyConfigValue(companyId, category, key);
	}

	/**
	 * 获取传入公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param companyId 公司编号。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static boolean getCompanyConfigBooleanValue(long companyId, String category, String key) {
		return isTrue(getConfigService().getCompanyConfigValue(companyId, category, key));
	}

	/**
	 * 获取当前平台的指定分类和键值的配置项的值。如果没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static String getPlatformConfigValue(String category, String key) {
		return getConfigService().getPlatformConfigValue(category, key);
	}

	/**
	 * 获取当前平台的指定分类和键值的配置项的值。如果没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static boolean getPlatformConfigBooleanValue(String category, String key) {
		return isTrue(getConfigService().getPlatformConfigValue(category, key));
	}

	/**
	 * 刷新所有配置项的缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:52
	 * </pre>
	 */
	public static void refreshConfigCache() {
		getConfigService().refreshConfigCache();
	}

	/**
	 * 把指定的配置项的值转换为等效的 boolean 类型值，一般情况下，1 表示 true，0 表示 false。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-08-08 14:43:42
	 * </pre>
	 * 
	 * @param configValue 要转换的配置值。
	 * @return true 或者 false
	 */
	public static boolean isTrue(String configValue) {
		return "1".equals(configValue);
	}
	
	

	/**
	 * 合并多个 JSON 格式的配置项。
	 * 
	 * <pre>
	 * Author: yujie
	 * Created Time: 2014-11-13 19:43:14
	 * </pre>
	 * 
	 * @param configValues JSON 格式的配置值数组
	 * @param idPath 形如 item,[,protlets,[,id
	 * @return 合并后的 JSON 格式配置项字符串。
	 */
	public static String mergeJsonConfig(String[] configValues, String idPath) {
		if(configValues == null || configValues.length <= 0)
			return "";
		// idPath: item,[,protlets,[,id
		String[] idPathArr = (idPath != null) ? idPath.split(",") : null;
		Object retJson = new JSONObject();
		for(String configValue : configValues) {
			if(configValue == null)
				continue;
			String valStr = configValue.trim();
			if(valStr.length() <= 0)
				continue;
			
			if(valStr.startsWith("[")) {
				JSONArray curJson = JSONArray.fromObject(configValue);
				if(retJson instanceof JSONArray) {
					doMergeJsonArray((JSONArray)retJson, curJson, idPathArr, 0);
				} else {
					if(!((JSONObject)retJson).isEmpty())
						curJson.add(retJson);
					retJson = curJson;
				}
			} else {
				JSONObject curJson = JSONObject.fromObject(configValue);
				if(retJson instanceof JSONArray) {
					((JSONArray)retJson).add(curJson);
				} else {
					doMergeJsonObject((JSONObject)retJson, curJson, idPathArr, 0);
				}		
			}
		}
		return retJson.toString();
	}
	
	/***
	 *  获取指定代理机构的配置项的值 <br/>
	 *  如果指定的代理机构的配置项的值为NULL的话  继续获取平台级别的配置 
	 * <pre>
	 * Author :cairw
	 * Created Time: 2015-05-04 13:28:14
	 * </pre>
	 * @param agentId
	 * @param category
	 * @param key
	 * @return
	 */
	public static String getAgentConfigValue(Long agentId,String category ,String key ){
		return getConfigService().getAgentConfigValue(agentId,category,key);
			
	}
	/***
	 *  获取当前代理机构的配置项的配置值  <br/>
	 *  如果指定的代理机构的配置项的值为NULL的话  继续获取平台级别的配置 
	 * <pre>
	 * Author :cairw
	 * Created Time: 2015-05-04 13:28:14
	 * </pre>
	 * @param category
	 * @param key
	 * @return
	 */
	public static String getAgentConfigValue(String category ,String key ){
		return getConfigService().getAgentConfigValue(WebUtils.getUserAgentId(),category,key);
			
	}
	
	/**
	 * 获取传入公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: cairw Created Time: 2015-05-18 21:34:45
	 * </pre>
	 * 
	 * @param agentId 代理机构ID。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static boolean getAgentConfigBooleanValue(long agentId, String category, String key) {
		return isTrue(getConfigService().getAgentConfigValue(agentId, category, key));
	}
	

	/**
	 * 获取传入公司对应的指定分类和键值的配置项的值。获取时按照公司级、公司类型级、平台级的顺序，返回
	 * 第一个匹配的值。如果在所有级别中都没有找到和指定的分类和键值匹配的配置项，将返回 NULL。 <br/>
	 * <br/>
	 * 该方法的实现中会对配置项的值做缓存以提升性能，如果在数据库中修改了配置项的值并且希望立即生效， 应该调用 refreshConfigCache
	 * 方法来刷新所有的配置项缓存。
	 * 
	 * <pre>
	 * Author: cairw Created Time: 2015-05-18 21:34:45
	 * </pre>
	 * 
	 * @param agentId 代理机构ID。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return 第一个匹配的级别的配置项值，如果没有找到匹配的配置项将返回 NULL。
	 */
	public static boolean getAgentConfigBooleanValue(String category, String key) {
		return isTrue(getConfigService().getAgentConfigValue(WebUtils.getUserAgentId(),category, key));
	}
	
	private static void doMergeJsonObject(JSONObject toObj, JSONObject fromObj, String[] idPathArr, int depth) {
		// 遍历源对象的所有属性合并到目标对象中
		for(Object keyObj : fromObj.keySet()) {
			// 如果目标 JSON 中不包括相同 Key 的属性，则直接复制该属性到目标对象中。
			if(!toObj.containsKey(keyObj)) {
				toObj.put(keyObj, fromObj.get(keyObj));
				continue;
			}

			Object fromSubObj = fromObj.get(keyObj);
			Object toSubObj = toObj.get(keyObj);
			
			String curPathKey = null;
			String[] curIdPathArr = null;
			if(idPathArr != null && idPathArr.length > depth) {
				curPathKey = idPathArr[depth].trim();
				if(curPathKey.equalsIgnoreCase((String)keyObj))
					curIdPathArr = idPathArr;
			}
			
			// 如果目标对象中此属性的值为数组
			if(toSubObj instanceof JSONArray) {
				JSONArray toSubArr = (JSONArray)toSubObj;
				// 如果源对象中此属性的值为数组
				if(fromSubObj instanceof JSONArray) {
					// 合并源对象的属性的数组值到目标对象的属性的数组值中
					JSONArray fromSubArr = (JSONArray)fromSubObj;
					doMergeJsonArray(toSubArr, fromSubArr, curIdPathArr, depth + 1);
				}
				else if(fromSubObj instanceof JSONObject) {
					JSONObject fromSubJsonObj = (JSONObject)fromSubObj;
					toSubArr.add(fromSubJsonObj);
				}
				// 如果源对象的属性值为原始类型，则忽略该属性
			} else if(toSubObj instanceof JSONObject) {
				JSONObject toSubJsonObj = (JSONObject)toSubObj;
				if(fromSubObj instanceof JSONArray) {
					JSONArray fromSubArr = (JSONArray)fromSubObj;
					fromSubArr.add(toSubJsonObj);
					toObj.put(keyObj, fromSubArr);
				} else if(fromSubObj instanceof JSONObject) {
					JSONObject fromSubJsonObj = (JSONObject)fromSubObj;
					doMergeJsonObject(toSubJsonObj, fromSubJsonObj, curIdPathArr, depth + 1);
				}
				// 如果源对象的属性值为原始类型，则忽略该属性
			}
			// 如果目标对象的属性值为原始类型，则忽略该属性
		}
	}
	
	private static void doMergeJsonArray(JSONArray toArr, JSONArray fromArr, String[] idPathArr, int depth) {
		for(int i = 0;i < fromArr.size(); i ++) {
			Object fromObji = fromArr.get(i);
			
			if(toArr.size() <= i) {
				toArr.add(fromObji);
				continue;
			}
			
			String[] curIdPathArr = null;
			if(idPathArr != null && idPathArr.length > depth) {
				String curPathKey = idPathArr[depth].trim();
				if(curPathKey.equalsIgnoreCase("["))
					curIdPathArr = idPathArr;
			}
			
			if(fromObji instanceof JSONArray) {
				JSONArray fromSubArr = (JSONArray)fromObji;
				Object toObji = toArr.get(i);
				if(toObji instanceof JSONArray) {
					JSONArray toSubArr = (JSONArray)toObji;
					// 合并源对象的属性的数组值到目标对象的属性的数组值中
					doMergeJsonArray(toSubArr, fromSubArr, curIdPathArr, depth + 1);
				} else if(toObji instanceof JSONObject) {
					JSONObject toSubJsonObj = (JSONObject)toObji;
					fromSubArr.add(toSubJsonObj);
					toArr.set(i, fromSubArr);
				}
				// 如果目标对象的属性值为原始类型，则忽略该属性
			} else if(fromObji instanceof JSONObject) {
				JSONObject fromSubJsonObj = (JSONObject)fromObji;
				if(curIdPathArr != null && curIdPathArr.length == (depth + 2)) {
					String id = curIdPathArr[depth + 1];
					if(fromSubJsonObj.containsKey(id)) {
						String idVal = fromSubJsonObj.getString(id);
						if(idVal == null)
							continue;
						boolean found = false;
						for(int j = 0;j < toArr.size(); j ++) {
							Object toObji = toArr.get(j);
							if(toObji instanceof JSONObject) {
								JSONObject toSubJsonObj = (JSONObject)toObji;
								if(toSubJsonObj.containsKey(id) && idVal.equalsIgnoreCase(toSubJsonObj.getString(id))) {
									found = true;
									break;
								}
							}
						}
						if(!found)
							toArr.add(fromObji);
						continue;
					}
				}
				
				Object toObji = toArr.get(i);
				if(toObji instanceof JSONArray) {
					JSONArray toSubArr = (JSONArray)toObji;
					toSubArr.add(fromSubJsonObj);
				} else if(toObji instanceof JSONObject) {
					JSONObject toSubJsonObj = (JSONObject)toObji;
					doMergeJsonObject(toSubJsonObj, fromSubJsonObj, curIdPathArr, depth + 1);
				}
				// 如果目标对象的属性值为原始类型，则忽略该属性
			}
			// 如果源对象的属性值为原始类型，则忽略该属性
		}
	}

	private static IConfigService getConfigService() {
		return UtilsServiceFactory.getService(IConfigService.class);
//		return (IConfigService) BeanFactory.findBean("IConfigService");
	}
}
