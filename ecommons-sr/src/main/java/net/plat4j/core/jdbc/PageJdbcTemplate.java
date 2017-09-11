package net.plat4j.core.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import net.plat4j.core.spring.BeanFactory;

// Referenced classes of package net.plat4j.core.jdbc:
//			ObjectRowMapper
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PageJdbcTemplate extends JdbcTemplate {

	private static DataSource ds;
	static {
		ds = BeanFactory.getBean("dataSource");
	}
	public PageJdbcTemplate() {
		super(ds);
	}

	public PageJdbcTemplate(DataSource ds) {
		
		super(ds);
	}


	public List findPage4Oracle(String queryString, Object values[], int pageNo, int pageSize,
			Class c) throws DataAccessException {
		StringBuffer pagingQuery = new StringBuffer(queryString.length() + 100);
		logger.debug((new StringBuilder("PageJdbcTemplate:pageSize:")).append(pageSize).toString());
		if (pageSize <= 0) {
			pagingQuery.append(queryString);
		} else {
			pagingQuery.append("select * from ( select row_.*, rownum rownum_ from ( ");
			pagingQuery.append(queryString);
			pagingQuery.append(" ) row_ where rownum <= ");
			pagingQuery.append(pageNo * pageSize);
			pagingQuery.append(") where rownum_ > ");
			pagingQuery.append((pageNo - 1) * pageSize);
		}
		logger.debug((new StringBuilder("PageJdbcTemplate:sql:")).append(pagingQuery.toString())
				.toString());
		return (List) super.query(pagingQuery.toString(), values, new RowMapperResultSetExtractor(
				new ObjectRowMapper(c)));
		// RowMapperResultReader(new ObjectRowMapper(c)));
	}

	public List find(String queryString, Object values[], Class c) throws DataAccessException {
		return (List) super.query(queryString, values, new RowMapperResultSetExtractor(
				new ObjectRowMapper(c)));
		// RowMapperResultReader(new ObjectRowMapper(c)));
	}
	  
	public Map<String, Object> queryForMap(String sql) throws DataAccessException {
		return (Map) queryForObject(sql, getColumnMapRowMapper());
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
		List results = query(sql, rowMapper);
		if (results == null || results.size() <= 0) {
			return null;
		}
		return (T) results.iterator().next();
	}

	public <T> T queryForObject(String sql, Object[] args, int[] argTypes,
			RowMapper<T> rowMapper) throws DataAccessException {
		List<T> results = (List) query(sql, args, argTypes, new RowMapperResultSetExtractor(
				rowMapper, 1));
		if (results == null || results.size() <= 0) {
			return null;
		}
		return (T) results.iterator().next();
	}

	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper)
			throws DataAccessException {
		List<T> results = (List) query(sql, args, new RowMapperResultSetExtractor(rowMapper, 1));
		if (results == null || results.size() <= 0) {
			return null;
		}
		return (T) results.iterator().next();
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
			throws DataAccessException {
		List<T> results = (List) query(sql, args, new RowMapperResultSetExtractor(rowMapper, 1));
		if (results == null || results.size() <= 0) {
			return null;
		}
		return (T) results.iterator().next();
	}

}
