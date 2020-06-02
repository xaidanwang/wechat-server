package com.github.aidan.mybatis.demo.type;

import com.alibaba.fastjson.JSON;
import com.github.aidan.mybatis.demo.dto.Quality;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wang yi fei
 * @date 2019/4/27 17:32
 */
@MappedTypes({Quality.class})
public class GenericTypeHandler <E extends Object> extends BaseTypeHandler<E> {

	private Class<E> type;
	public GenericTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		String json = JSON.toJSONString(parameter);
		ps.setString(i, json);
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return parseJson(rs.getString(columnName));
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return parseJson(rs.getString(columnIndex));
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return parseJson(cs.getString(columnIndex));
	}

	private E parseJson(String json) {
		return JSON.parseObject(json, type);
	}
}
