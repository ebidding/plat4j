/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年8月22日 下午3:38:14
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年8月22日下午3:38:14		WGY			Create file
=========================================================================
*/

package org.net.plat4j.common.jdbc;

/**
 * jdbc常量
 *
 */
public interface JdbcConstants {
	 public static final String JTDS              = "jtds";

    public static final String MOCK              = "mock";

    public static final String HSQL              = "hsql";

    public static final String DB2               = "db2";

    public static final String DB2_DRIVER        = "COM.ibm.db2.jdbc.app.DB2Driver";

    public static final String POSTGRESQL        = "postgresql";
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

    public static final String SYBASE            = "sybase";

    public static final String SQL_SERVER        = "sqlserver";
    public static final String SQL_SERVER_DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

    public static final String ORACLE            = "oracle";
    public static final String ORACLE_DRIVER     = "oracle.jdbc.OracleDriver";

    public static final String ALI_ORACLE        = "AliOracle";
    public static final String ALI_ORACLE_DRIVER = "com.alibaba.jdbc.AlibabaDriver";

    public static final String MYSQL             = "mysql";
    public static final String MYSQL_DRIVER      = "com.mysql.jdbc.Driver";

    public static final String MARIADB           = "mariadb";
    public static final String MARIADB_DRIVER    = "org.mariadb.jdbc.Driver";

    public static final String DERBY             = "derby";

    public static final String HBASE             = "hbase";

    public static final String HIVE              = "hive";

    public static final String H2                = "h2";
    public static final String H2_DRIVER         = "org.h2.Driver";

    public static final String DM                = "dm";
    public static final String DM_DRIVER         = "dm.jdbc.driver.DmDriver";
    
    public static final String KINGBASE          = "kingbase";
    public static final String KINGBASE_DRIVER   = "com.kingbase.Driver";
    
    /**
     * 阿里云odps
     */
    public static final String ODPS              = "odps";

    /**
     * Log4JDBC
     */
    public static final String LOG4JDBC          = "log4jdbc";
    public static final String LOG4JDBC_DRIVER   = "net.sf.log4jdbc.DriverSpy";
}
