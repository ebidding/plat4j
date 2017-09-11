package org.net.plat4j.sr.core.base.generatecode.generator;

public interface IHbmGenerator {
	
	/**
	 * 设置数据库驱动名称
	 *
	 * @param    driveClassName  数据库驱动名称
	 */
	public void setDriveClassName(String driveClassName);
	/**
	 * 设置jdbc路径
	 *
	 * @param    jdbcUrl  jdbc路径
	 */
	public void setJdbcUrl(String jdbcUrl);

	/**
	 * 设置连接数据库的用户名
	 *
	 * @param    username  用户名
	 */
	public void setUsername(String username);

	/**
	 * 设置连接数据库的密码
	 *
	 * @param    password  密码
	 */
	public void setPassword(String password);

	/**
	 * 设置输入路径
	 *
	 * @param    outputDir  输入路径
	 */
	public void setOutputDir(String outputDir);
	/**
	 * 设置输出实体类包名
	 *
	 * @param    packageName  输出实体类包名
	 */
	public void setPackageName(String packageName) ;
	
	/**
	 * 添加数据库表名
	 *
	 * @param 	 tableName  数据库表名
	 */
	public void addTableName(String tableName );
	
	/**
	 * 生成实体类
	 */
	public void generateEntity();
}
