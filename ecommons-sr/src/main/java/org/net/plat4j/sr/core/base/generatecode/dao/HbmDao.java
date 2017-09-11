/*
	Copyright (C) 2016 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2016年5月24日 上午9:45:14
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2016年5月24日上午9:45:14		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.sr.core.base.generatecode.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.net.plat4j.sr.core.base.generatecode.model.Key;
import org.net.plat4j.sr.core.base.generatecode.model.Property;


/**
 * @author chenyj
 *
 */
public class HbmDao {
	/**
	 * 查询表所有字段信息
	 * @param connection 数据库连接
	 * @param tableName 数据库表名称
	 * @return 该表的所有的字段
	 * @throws SQLException
	 */
	public List<Property> queryProperties(Connection connection, String tableName) throws SQLException{
		String sql = "SELECT COLUMN_NAME columnName, DATA_TYPE dataType, DATA_LENGTH dataLength, "
				+ " DATA_PRECISION dataPrecision, DATA_SCALE dataScale "
				+ " FROM USER_TAB_COLUMNS WHERE TABLE_NAME = ? ORDER BY COLUMN_ID ASC ";
		QueryRunner queryRunner = new QueryRunner();
		List<Property> list = (List<Property>) queryRunner.query(connection, sql, new BeanListHandler(Property.class), new String[]{tableName});
		return list;
	}
	/**
	 * 查询表的键的信息
	 * @param connection 数据库连接
	 * @param tableName 表名称
	 * @param keyType 字段类型（ C CHECK类型,P 主键,U 唯一性约束,R 外键,V视图中CHECK,O视图中只读）
	 * @return
	 * @throws SQLException
	 */
	public List<Key> queryKey(Connection connection, String tableName, String keyType) throws SQLException{
		String sql = "SELECT ucc.column_name columnName,ucc.position position FROM user_cons_columns ucc, user_constraints uc "
				+ " WHERE ucc.constraint_name = uc.constraint_name AND uc.status = 'ENABLED' "
				+ " AND ucc.TABLE_NAME = ? AND uc.constraint_type = ? "
				+ " ORDER BY ucc.position ASC";
		QueryRunner queryRunner = new QueryRunner();
		List<Key> list = (List<Key>) queryRunner.query(connection, sql, new BeanListHandler(Key.class), new String[]{tableName, keyType});
		return list;
	}
}
