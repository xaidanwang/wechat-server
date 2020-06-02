package com.github.aidan.java8.test.design.proxy;



/**
 * @author wang yi fei
 * @date 2019/6/12 20:31
 */
public class PrinterProxy implements Printable {
	private String name;
	private Printable real;
	private String classname;
	public PrinterProxy() {
	}

	public PrinterProxy(String name,String classname) {
		this.name = name;
	}

	@Override
	public synchronized void setPrinterName(String name) {
		if (real != null){
			real.setPrinterName(name);
		}
		this.name = name;
	}

	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String string) {
		realize();
		real.print(string);
	}

	private synchronized void realize(){
		if (real == null){
			try {
				real = (Printable)Class.forName(classname).newInstance();
				real.setPrinterName(name);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
