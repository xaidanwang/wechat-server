package com.github.aidan.mybatis.demo.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author wang yi fei
 * @date 2019/11/2 16:05
 */
@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})})
public class MyPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		return null;
	}

	@Override
	public Object plugin(Object target) {
		return null;
	}

	@Override
	public void setProperties(Properties properties) {

	}
}
