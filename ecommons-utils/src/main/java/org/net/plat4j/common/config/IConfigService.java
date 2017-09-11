/*
	Copyright (C) 2014 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2014-08-07 14:53:56
	
	Revision History:
	Version     Date              				Author			Comments
	1.0         	2014-08-07 14:53:56		yujie				Create file
=========================================================================
 */

package org.net.plat4j.common.config;

/**
 * 配置管理访问接口。
 * 
 * @author yujie
 */
public interface IConfigService {
	/**
	 * 平台级配置。
	 */
	public static final int CONFIG_SCOPE_PLATFORM = 10;
	/**
	 * 公司类型级配置。
	 */
	public static final int CONFIG_SCOPE_COMPANY_TYPE = 20;
	
	
	public static final  int COFNIG_SCOPE_AGENT=21;
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
	 * @param scope 配置项级别，可以使用 IConfigService 中的几个常量。
	 * @param scopeId 配置项级别对应对象的编号，对于平台级始终为 0，对于公司类型级为参见公司类型定义， 对于公司级为
	 *            SYS_COMPANY_ID，对于部门级为 SYS_COMPANY_DEPART_ID，对于角色级为 SYS_ROLE_ID，对于用户级为 SYS_USER_ID。
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @param value 需要保存的配置项值。
	 * @return 保存是否成功。
	 */
	boolean saveConfigValue(int scope, long scopeId, String category, String key, String value);

	/**
	 * 删除用户的配置。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:45
	 * </pre>
	 * 
	 * @param userId 用户编号
	 * @param category 配置项分类代码，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的代码。
	 * @param key 配置项键值，将被转换为全部大写，所以不应该仅以大小写不同来区分不同的键值。
	 * @return
	 */
	void removeUserConfig(long userId, String category, String key);

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
	String getUserConfigValue(String category, String key);
	
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
	String[] getUserConfigValues(String category, String key);

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
	String getCompanyConfigValue(long companyId, String category, String key);

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
	String getPlatformConfigValue(String category, String key);

	/**
	 * 刷新所有配置项的缓存。
	 * 
	 * <pre>
	 * Author: yujie Created Time: 2014-08-07 21:34:52
	 * </pre>
	 * 
	 */
	void refreshConfigCache();

	String getAgentConfigValue(Long agentId, String category, String key);
}
