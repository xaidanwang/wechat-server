package com.github.aidan.java.design.study.active;

/**
 * @author wang yi fei
 * @date 2020/5/29 10:52
 */
public class DispalyStringRequest extends MethodRequest<String> {

	private final String string;
	protected DispalyStringRequest(Servant servant,String string) {
		super(servant, null);
		this.string = string;
	}

	@Override
	public void execute() {
		servant.displayString(string);
	}
}
