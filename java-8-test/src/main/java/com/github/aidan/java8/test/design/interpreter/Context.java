package com.github.aidan.java8.test.design.interpreter;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.StringTokenizer;

/**
 * @author wang yi fei
 * @date 2019/6/14 14:31
 */
public class Context {
	private StringTokenizer tokenizer;
	private String currentToken;

	public Context(String text) {
		this.tokenizer = new StringTokenizer(text);
		nextToken();
	}
	public String nextToken(){
		if (tokenizer.hasMoreTokens()){
			currentToken = tokenizer.nextToken();
		}else {
			currentToken = null;
		}
		return currentToken;
	}
	public String currentToken(){
		return currentToken;
	}

	public void skipToken(String token) throws ParseException{
		if (!token.equals(currentToken)){
			throw new ParseException("Warning: " + token + " is  expected, but " + currentToken + " is found");
		}
		nextToken();
	}
	public int currentNumber()throws ParseException{
		int number = 0;
		try {
			number = Integer.parseInt(currentToken);
		}catch (NumberFormatException e){
			throw new ParseException("Warning: " + e);
		}
		return number;
	}
}
