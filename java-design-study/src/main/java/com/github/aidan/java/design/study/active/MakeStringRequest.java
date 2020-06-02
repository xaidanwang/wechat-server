package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/29 10:47
 */
public class MakeStringRequest extends MethodRequest<String> {

	private final int count;
	private final char fillchar;


	protected MakeStringRequest(Servant servant, FutureResult<String> future,int count,char fillchar) {
		super(servant, future);
		this.count = count;
		this.fillchar = fillchar;
	}

	@Override
	public void execute() {
		Result<String> result =  servant.makeString(count,fillchar);
		future.setResult(result);
	}
}
