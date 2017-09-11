/*
	Copyright (C) 2015 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: yujie
	Version: 1.0
	Created Time: 2015-06-08 09:54:28
	
	Revision History:
	Version         Date               		Author			Comments
	1.0         	2015-06-08 09:54:28		yujie			Create file
=========================================================================
*/

package org.net.plat4j.sr.core.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yujie
 *
 */
public interface IBatchRowSetter<T> {
	void execute(PreparedStatement ps, T t) throws SQLException;
}
