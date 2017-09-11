/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-06-08 10:26:23
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-06-08 10:26:23		yujie			Create file
=========================================================================
*/

package org.net.plat4j.sr.core.base;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author yujie
 *
 *	数据库事务的执行语句部分，用语 BaseDao.doTransaction 方法。
 */
public interface ITransactionBody {
	/**
	 * 在事务中执行数据库操作。
	 * 
	 * @param conn 可用于执行数据库操作的连接，其事务由外部统一管理。
	 * @return 执行成功返回 true，否则放回 false。
	 * @throws SQLException
	 */
	Boolean exec(Connection conn) throws SQLException;
}
