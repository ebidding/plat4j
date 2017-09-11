package org.net.plat4j.sr.core.base.generatecode.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.net.plat4j.sr.core.base.generatecode.model.Entity;

public abstract class AbstractHbmGenerator implements IHbmGenerator{

	/**
	 * 数据库驱动名称
	 */
	protected String driveClassName;
	/**
	 * jdbc路径
	 */
	protected String jdbcUrl;
	/**
	 * 连接数据库的用户名
	 */
	protected String username;
	/**
	 * 连接数据库的密码
	 */
	protected String password;
	/**
	 * 输入路径
	 */
	protected String outputDir;
	/**
	 * 数据库表名
	 */
	protected List<String> tableNames;
	/**
	 * 输出实体类包名
	 */
	protected String packageName;
	
	public AbstractHbmGenerator(){
		this.tableNames = new ArrayList<String>();
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setDriveClassName(String driveClassName) {
		this.driveClassName = driveClassName;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
	/**
	 * 添加数据库表名
	 *
	 * @param 	 tableName  数据库表名
	 */
	public void addTableName(String tableName ){
		this.tableNames.add(tableName);
	}
	
	
	/**
	 * 生成实体类
	 */
	public void generateEntity(){
		if(tableNames.size()>0){
			for(String tablename:tableNames){
				build(packageName , tablename, outputDir);
			}
		}else{
			throw new RuntimeException("The table name  must not be null.");
		}
	}
	
	public abstract void build(String packageName, String tableName, String outputDir);

	/**
	 * 根据表名构建实体
	 * @param tableName
	 * @return
	 */
	public abstract Entity buildEntity(String tableName);

	/**
	 * 构建hibernate实体类
	 * @param packageName 报名
	 * @param entity
	 * @return
	 */
	public abstract String buildContent(String packageName, Entity entity);

	/**
	 * 写入至文件
	 * @param context
	 * @param file
	 */
	public abstract void output2File(String content, File file);

	/**
	 * 构建联合主键
	 * @param packageName
	 * @param entity
	 * @return
	 */
	public abstract String buildComposePk(String packageName, Entity entity);

}