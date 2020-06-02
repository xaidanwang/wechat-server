package com.github.aidan.java8.test.design.chain;

/**
 * @author wang yi fei
 * @date 2019/5/25 15:25
 */
public abstract class Support {
	private String name;
	private Support next;

	public Support(String name) {
		this.name = name;
	}

	public Support setNext(Support next) {
		this.next = next;
		return next;
	}

/*	public final void support(Trouble trouble){
		if (resolve(trouble)){
			done(trouble);
		}else if (next != null){
			next.support(trouble);
		}else {
			fail(trouble);
		}
	}*/
	public final void support(Trouble trouble){

		for (Support obj =this;true;obj = obj.next){
			if (obj.resolve(trouble)){
				obj.done(trouble);
				break;
			}else if (obj.next == null){
				obj.fail(trouble);
				break;
			}
		}
	}
	@Override
	public String toString() {
		return "[" + name + "]";
	}

	protected abstract boolean resolve(Trouble trouble);
	protected void done(Trouble trouble){
		System.out.println(trouble + "is resolve by "+ this + ".");
	}

	protected void fail(Trouble trouble) {// 未解决

		System.out.println(trouble + " cannot be  resolve") ;
	}

}
